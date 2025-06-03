package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.entities.condicionesAlerta.ICondicionAlerta;
import ar.utn.ba.ddsi.mailing.models.repositories.IClimaRepository;
import ar.utn.ba.ddsi.mailing.services.IAlertasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;

@Service
public class AlertasService implements IAlertasService {
    private static final Logger logger = LoggerFactory.getLogger(AlertasService.class);

    private final ICondicionAlerta condicionesAlerta; // no me queda claro como setearía las condiciones que deseamos
    private final IClimaRepository climaRepository;
    private final EmailService emailService;
    private final String remitente;
    private final List<String> destinatarios;

    public AlertasService(
            ICondicionAlerta condicionesAlerta,
            IClimaRepository climaRepository, 
            EmailService emailService,
            @Value("${email.alertas.remitente}") String remitente,
            @Value("${email.alertas.destinatarios}") String destinatarios) {
        this.condicionesAlerta = condicionesAlerta;
        this.climaRepository = climaRepository;
        this.emailService = emailService;
        this.remitente = remitente;
        this.destinatarios = Arrays.asList(destinatarios.split(","));
    }

    @Override
    public Mono<Void> generarAlertasYAvisar() {
        return Mono.fromCallable(() -> climaRepository.findByProcesado(false))
            .flatMap(climas -> {
                logger.info("Procesando {} registros de clima no procesados", climas.size());
                return Mono.just(climas);
            })
            .flatMap(climas -> {
                climas.stream()
                    .filter(this::cumpleCondicionesAlerta)
                    .forEach(this::generarYEnviarEmail);
                
                // Marcar todos como procesados
                climas.forEach(clima -> {
                    clima.setProcesado(true);
                    climaRepository.save(clima);
                });
                
                return Mono.empty();
            })
            .onErrorResume(e -> {
                logger.error("Error al procesar alertas: {}", e.getMessage());
                return Mono.empty();
            })
            .then();
    }

    private boolean cumpleCondicionesAlerta(Clima clima) {
        return condicionesAlerta.seCumpleAlerta(clima);
    }

    private void generarYEnviarEmail(Clima clima) {
        String asunto = "Alerta de Clima - Condiciones Extremas";
        String mensaje = clima.descripcion();

        for (String destinatario : destinatarios) {
            Email email = new Email(destinatario, remitente, asunto, mensaje);
            emailService.crearEmail(email);
        }
        
        logger.info("Email de alerta generado para {} - Enviado a {} destinatarios", 
            clima.getCiudad(), destinatarios.size());
    }
} 
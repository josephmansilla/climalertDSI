package ar.utn.ba.ddsi.mailing.models.entities.notificacion;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email implements INotificacion {
    private Long id;
    private String destinatario;
    private String remitente;
    private String asunto;
    private String contenido;
    private boolean enviado;

    private Timestamp fechaEnviado;

    public Email(String destinatario, String remitente, String asunto, String contenido) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.asunto = asunto;
        this.contenido = contenido;
        this.enviado = false;
    }

    public void enviar(Email email) {
        adapter.enviar(Email email);
    }
} 
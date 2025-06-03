package ar.utn.ba.ddsi.mailing.models.entities;

import ar.utn.ba.ddsi.mailing.models.entities.notificacion.INotificationAdapter;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email implements INotificationAdapter {
    private Long id;
    private String destinatario;
    private String remitente;
    private String asunto;
    private String contenido;
    private boolean enviado;

    private Timestamp fechaEnviado;
    private INotificationAdapter notificacionAdapter;

    public Email(String destinatario, String remitente, String asunto, String contenido) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.asunto = asunto;
        this.contenido = contenido;
        this.notificacionAdapter = notificacionAdapter;
        this.enviado = false;
    }

    public Email(String destinatario, String remitente, String asunto, String contenido, INotificationAdapter notificacionAdapter) {
        this.destinatario = destinatario;
        this.remitente = remitente;
        this.asunto = asunto;
        this.contenido = contenido;
        this.notificacionAdapter = notificacionAdapter;
        this.enviado = false;
    }

    public void enviar(Email email) {
        notificacionAdapter.enviar(email);
    }
} 
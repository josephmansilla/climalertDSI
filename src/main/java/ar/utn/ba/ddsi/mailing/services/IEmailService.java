package ar.utn.ba.ddsi.mailing.services;

import ar.utn.ba.ddsi.mailing.models.entities.notificacion.Email;
import java.util.List;

public interface IEmailService {
    Email crearEmail(Email email);
    List<Email> obtenerEmails(Boolean pendiente);
    void procesarPendientes();
    void loguearEmailsPendientes();
} 
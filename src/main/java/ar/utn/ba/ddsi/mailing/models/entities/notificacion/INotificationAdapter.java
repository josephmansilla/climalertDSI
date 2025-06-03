package ar.utn.ba.ddsi.mailing.models.entities.notificacion;

import ar.utn.ba.ddsi.mailing.models.entities.Email;

public interface INotificationAdapter {
  public void enviar(Email email);
}

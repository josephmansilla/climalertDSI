package ar.utn.ba.ddsi.mailing.models.entities.alerta;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import java.sql.Timestamp;
import lombok.Getter;

public interface IAlerta {
  @Getter
  Timestamp fechaActualizacion = new Timestamp(System.currentTimeMillis());
  public Boolean seCumpleAlerta(Clima clima);
}

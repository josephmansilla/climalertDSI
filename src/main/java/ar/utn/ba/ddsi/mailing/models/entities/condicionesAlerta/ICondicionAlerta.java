package ar.utn.ba.ddsi.mailing.models.entities.condicionesAlerta;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import java.sql.Timestamp;
import lombok.Getter;

public interface ICondicionAlerta {
  public Boolean seCumpleAlerta(Clima clima);
}

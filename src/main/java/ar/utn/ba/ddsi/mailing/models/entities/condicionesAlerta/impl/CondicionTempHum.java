package ar.utn.ba.ddsi.mailing.models.entities.condicionesAlerta.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.condicionesAlerta.ICondicionAlerta;
import org.springframework.stereotype.Component;

@Component
public class CondicionTempHum implements ICondicionAlerta {
  private final Integer temperaturaLimiteEnCelsius;
  private final Integer temperaturaLimiteEnFahrenheit;
  private final Double humedadLimite;
  private final String asunto;


  public CondicionTempHum() {
    temperaturaLimiteEnCelsius = 35;
    temperaturaLimiteEnFahrenheit = 95;
    humedadLimite = 0.6;
    asunto = "Alerta de Clima - Condiciones Extremas";
  }
  @Override
  public Boolean seCumpleAlerta(Clima clima) {
    return
        clima.getHumedad() >= humedadLimite &&
            (clima.getTemperaturaCelsius() >= temperaturaLimiteEnCelsius ||
                clima.getTemperaturaFahrenheit() >= temperaturaLimiteEnFahrenheit);
  }
}

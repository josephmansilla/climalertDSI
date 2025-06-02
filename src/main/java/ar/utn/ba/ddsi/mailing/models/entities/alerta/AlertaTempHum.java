package ar.utn.ba.ddsi.mailing.models.entities.alerta;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;

public class AlertaTempHum implements IAlerta {
  private final Integer temperaturaLimiteEnCelsius;
  private final Integer temperaturaLimiteEnFahrenheit;
  private final Double humedadLimite;

  public AlertaTempHum() {
    temperaturaLimiteEnCelsius = 35;
    temperaturaLimiteEnFahrenheit = 95;
    humedadLimite = 0.6;
  }

  public Boolean seCumpleAlerta(Clima clima) {
    return
        clima.getHumedad() >= humedadLimite &&
            (clima.getTemperaturaCelsius() >= temperaturaLimiteEnCelsius ||
                clima.getTemperaturaFahrenheit() >= temperaturaLimiteEnFahrenheit);
  }
}

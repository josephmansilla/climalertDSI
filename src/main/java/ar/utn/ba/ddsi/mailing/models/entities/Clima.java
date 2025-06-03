package ar.utn.ba.ddsi.mailing.models.entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Clima {
    private Long id;
    private String ciudad;
    private String region;
    private String pais;
    private Double temperaturaCelsius;
    private Double temperaturaFahrenheit;
    private String condicion;
    private Double velocidadVientoKmh;
    private Integer humedad;
    private LocalDateTime fechaActualizacion;
    private boolean procesado;

    public Clima() {
        this.fechaActualizacion = LocalDateTime.now();
        this.procesado = false;
    }

    public String descripcion() {
        return String.format(
            "ALERTA: Condiciones climáticas extremas detectadas en %s\n\n" +
                "Temperatura: %.1f°C\n" +
                "Humedad: %d%%\n" +
                "Condición: %s\n" +
                "Velocidad del viento: %.1f km/h\n\n" +
                "Se recomienda tomar precauciones.",
            this.ciudad,
            this.temperaturaCelsius,
            this.humedad,
            this.condicion,
            this.velocidadVientoKmh
        );
    }
} 
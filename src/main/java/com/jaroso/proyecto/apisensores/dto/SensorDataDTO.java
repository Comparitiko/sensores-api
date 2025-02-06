package com.jaroso.proyecto.apisensores.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorDataDTO {
    @JsonProperty("sensor_id")
    private String sensorId;
    private double value;

    public SensorDataDTO(String sensorId, double value) {
        this.sensorId = sensorId;
        this.value = value;
    }

}

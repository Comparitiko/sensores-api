package com.jaroso.proyecto.apisensores.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorDataDto {
    private String sensorId;
    private double value;

    public SensorDataDto(String sensorId, double value) {
        this.sensorId = sensorId;
        this.value = value;
    }

}

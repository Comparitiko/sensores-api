package com.jaroso.proyecto.apisensores.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorData {
    private String sensorId;
    private double value;

    public SensorData(String sensorId, double value) {
        this.sensorId = sensorId;
        this.value = value;
    }

}

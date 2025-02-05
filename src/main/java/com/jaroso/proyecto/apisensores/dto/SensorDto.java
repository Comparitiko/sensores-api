package com.jaroso.proyecto.apisensores.dto;

import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.enums.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDto {
  private SensorType sensorType;
  private String location;
  private Double latitude;
  private Double longitude;
  private Unit unit;
  private Long plantationId;

  public SensorDto(
    SensorType sensorType,
    String location,
    Double latitude,
    Double longitude,
    Unit unit,
    Long plantationId
  ) {
    this.sensorType = sensorType;
    this.location = location;
    this.latitude = latitude;
    this.longitude = longitude;
    this.unit = unit;
    this.plantationId = plantationId;
  }


}

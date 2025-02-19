package com.jaroso.proyecto.apisensores.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SensorDTO {
  @JsonProperty("sensor_type")
  private SensorType sensorType;

  private String location;

  private Double latitude;

  private Double longitude;

  private Unit unit;

  @JsonProperty("plantation_id")
  private Long plantationId;
}

package com.jaroso.proyecto.apisensores.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantationDTO {

    private String name;

    private String ubication;

    private String country;

    private String province;

    private String city;

    private String coordinates;

    private String plantationType;

    @JsonProperty("sensors")
    private List<Sensor> sensors;

}

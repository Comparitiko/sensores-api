package com.jaroso.proyecto.apisensores.controllers;

import com.jaroso.proyecto.apisensores.dto.SensorData;
import org.springframework.web.bind.annotation.*;
import com.jaroso.proyecto.apisensores.services.SensorService;

@RestController
@RequestMapping("/prueba")
public class prueba {
    private final SensorService sensorService;

    public prueba(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public String writeData(@RequestBody SensorData data) {
        sensorService.saveData(data.getSensorId(), data.getValue());
        return "Datos escritos correctamente";
    }

}

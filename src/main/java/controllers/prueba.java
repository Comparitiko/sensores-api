package controllers;

import dto.SensorData;
import org.springframework.web.bind.annotation.*;
import services.SensorService;

@RestController
@RequestMapping("/prueba")
public class prueba {
    private SensorService sensorService;


    public void SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public prueba(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public String writeData(@RequestBody SensorData data) {
        sensorService.saveData(data.getSensorId(), data.getValue());
        return "Datos escritos correctamente";
    }

}

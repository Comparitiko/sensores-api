package com.jaroso.proyecto.apisensores.controllers;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorData;
import com.jaroso.proyecto.apisensores.models.Sensor;
import com.jaroso.proyecto.apisensores.services.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
  private final SensorService sensorService;

  public SensorController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  // Get all sensors
  @GetMapping
  public List<Sensor> getSensors() {
    return sensorService.getAllSensors();
  }

  // Save a new sensor
  @PostMapping
  public Sensor saveSensor(Sensor sensor) {
    return sensorService.saveSensor(sensor);
  }

  // Get a sensor by id
  @GetMapping("/{id}")
  public Sensor getSensorById(@PathVariable String id) {
    return sensorService.getSensorById(id);
  }

  // Get all data for a specific sensor
  @GetMapping("/{id}/data")
  public List<FluxTable> getDataByLocation(@PathVariable String id) {
    return sensorService.getDataByLocation(id);
  }

  // Get all the sensors of a specific type
  @GetMapping("/{type}")
  public List<Sensor> getSensorsByType(@PathVariable String type) {
    return sensorService.getSensorsByType(type);
  }

  // Save data for a specific sensor
  @PostMapping("/{id}/data")
  public void saveData(SensorData sensorData) {
    sensorService.saveData(sensorData.getSensorId(), sensorData.getValue());
  }
}

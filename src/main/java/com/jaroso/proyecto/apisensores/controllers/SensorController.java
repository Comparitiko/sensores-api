package com.jaroso.proyecto.apisensores.controllers;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDto;
import com.jaroso.proyecto.apisensores.dto.SensorDto;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
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
  public Sensor saveSensor(@RequestBody SensorDto sensor) {
    return sensorService.saveSensor(sensor);
  }

  // Get a sensor by id
  @GetMapping("/{id}")
  public Sensor getSensorById(@PathVariable Long id) {
    return sensorService.getSensorById(id);
  }

  // Get all data for a specific sensor
  @GetMapping("/{id}/data")
  public List<FluxTable> getDataBySensor(@PathVariable Long id) {
    return sensorService.getDataBySensor(id);
  }

  // Get all the sensors of a specific type
  @GetMapping("/{type}")
  public List<Sensor> getSensorsByType(@PathVariable SensorType type) {
    return sensorService.getSensorsByType(type);
  }

  // Save data for a specific sensor
  @PostMapping("/{id}/data")
  public void saveData(@PathVariable Long id, @RequestBody SensorDataDto sensorDataDto) {
    sensorService.saveDataOfSensor(id, sensorDataDto);
  }

  // Get data of a specific location
  @GetMapping("/{location}/data")
  public List<FluxTable> getDataByLocation(@PathVariable String location) {
    return sensorService.getDataByLocation(location);
  }

  @DeleteMapping("/{id}")
  public void deleteSensor(@PathVariable Long id) {
    sensorService.deleteSensorById(id);
  }
}

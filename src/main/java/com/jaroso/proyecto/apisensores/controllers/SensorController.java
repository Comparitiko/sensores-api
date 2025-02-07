package com.jaroso.proyecto.apisensores.controllers;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDTO;
import com.jaroso.proyecto.apisensores.dto.SensorDTO;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import com.jaroso.proyecto.apisensores.services.SensorService;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<?> getSensors() {
    return ResponseEntity.ok(sensorService.getAllSensors());
  }

  // Save a new sensor
  @PostMapping
  public ResponseEntity<?> saveSensor(@RequestBody SensorDTO sensor) {
    return ResponseEntity.ok(sensorService.saveSensor(sensor));
  }

  // Get a sensor by id
  @GetMapping("/{id}")
  public ResponseEntity<?> getSensorById(@PathVariable Long id) {
    return ResponseEntity.ok(sensorService.getSensorById(id));
  }

  // Get all data for a specific sensor
  @GetMapping("/{id}/data")
  public ResponseEntity<?> getDataBySensor(@PathVariable Long id) {
    return ResponseEntity.ok(sensorService.getDataBySensor(id));
  }

  // Get all the sensors of a specific type
  @GetMapping("/{type}")
  public ResponseEntity<?> getSensorsByType(@PathVariable SensorType type) {
    return ResponseEntity.ok(sensorService.getSensorsByType(type));
  }

  // Save data for a specific sensor
  @PostMapping("/{id}/data")
  public ResponseEntity<?> saveData(@PathVariable Long id, @RequestBody SensorDataDTO sensorDataDto) {
    sensorService.saveDataOfSensor(id, sensorDataDto);
    return ResponseEntity.ok(sensorDataDto);
  }

  // Get data of a specific location
  @GetMapping("/{location}/data")
  public ResponseEntity<?> getDataByLocation(@PathVariable String location) {
    return ResponseEntity.ok(sensorService.getDataByLocation(location));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSensor(@PathVariable Long id) {
    sensorService.deleteSensorById(id);
    return ResponseEntity.ok("Sensor deleted successfully");
  }
}

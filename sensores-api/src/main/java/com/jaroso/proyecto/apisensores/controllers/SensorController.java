package com.jaroso.proyecto.apisensores.controllers;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDTO;
import com.jaroso.proyecto.apisensores.dto.SensorDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import com.jaroso.proyecto.apisensores.services.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    return sensorService.getAllSensors();
  }

  // Save a new sensor
  @PostMapping
  public ResponseEntity<?> saveSensor(@RequestBody SensorDTO sensor) {
    return sensorService.saveSensor(sensor);
  }

  // Get a sensor by id
  @GetMapping("/{id}")
  public ResponseEntity<?> getSensorById(@PathVariable Long id) {
    return sensorService.getSensorById(id);
  }

  // Get all sensors of a specific plantation
  @GetMapping("/plantation/{plantation_id}")
  public ResponseEntity<?> getSensorByPlantation(@PathVariable Long plantation_id) {
    return sensorService.getSensorByPlantation(plantation_id);
  }

  // Get all data for a specific sensor
  @GetMapping("/{id}/data")
  public ResponseEntity<?> getDataBySensor(@PathVariable Long id) {
    return ResponseEntity.ok(sensorService.getDataBySensor(id));
  }

  // Get all the sensors of a specific type
  @GetMapping("/type/{type}")
  public ResponseEntity<?> getSensorsByType(@PathVariable SensorType type) {
    return sensorService.getSensorsByType(type);
  }

  // Save data for a specific sensor
  @PostMapping("/data/{id}")
  public ResponseEntity<?> saveData(@PathVariable Long id, @RequestBody SensorDataDTO sensorDataDto) {
    return sensorService.saveDataOfSensor(id, sensorDataDto);
  }

  // Get data of a specific location
  @GetMapping("/data/{location}")
  public ResponseEntity<?> getDataByLocation(@PathVariable String location) {
    return ResponseEntity.ok(sensorService.getDataByLocation(location));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSensor(@PathVariable Long id) {
    return sensorService.deleteSensorById(id);
  }
}

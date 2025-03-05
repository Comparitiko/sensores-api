package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDTO;
import com.jaroso.proyecto.apisensores.dto.SensorDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
  ResponseEntity<?> getAllSensors();
  ResponseEntity<?> saveSensor(SensorDTO sensor);
  ResponseEntity<?> getSensorById(Long id);
  ResponseEntity<?> getDataBySensor(Long id);
  ResponseEntity<?> getSensorsByType(SensorType sensorType);
  ResponseEntity<?> saveDataOfSensor(Long sensorId, SensorDataDTO sensorDataDto);
  ResponseEntity<?> getDataByLocation(String location);
  ResponseEntity<?> deleteSensorById(Long id);
  ResponseEntity<?> getSensorByPlantation(Long plantation_id);
}

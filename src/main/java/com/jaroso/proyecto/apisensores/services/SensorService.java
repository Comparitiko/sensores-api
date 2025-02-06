package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDTO;
import com.jaroso.proyecto.apisensores.dto.SensorDTO;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
  List<Sensor> getAllSensors();
  Sensor saveSensor(SensorDTO sensor);
  Sensor getSensorById(Long id);
  List<FluxTable> getDataBySensor(Long id);
  List<Sensor> getSensorsByType(SensorType sensorType);
  SensorDataDTO saveDataOfSensor(Long sensorId, SensorDataDTO sensorDataDto);
  List<FluxTable> getDataByLocation(String location);
  Sensor deleteSensorById(Long id);
}

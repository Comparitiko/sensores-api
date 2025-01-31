package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDto;
import com.jaroso.proyecto.apisensores.dto.SensorDto;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.models.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
  List<Sensor> getAllSensors();
  Sensor saveSensor(SensorDto sensor);
  Sensor getSensorById(Long id);
  List<FluxTable> getDataBySensor(Long id);
  List<Sensor> getSensorsByType(SensorType sensorType);
  SensorDataDto saveDataOfSensor(Long sensorId,SensorDataDto sensorDataDto);
  List<FluxTable> getDataByLocation(String location);
}

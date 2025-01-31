package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDto;
import com.jaroso.proyecto.apisensores.dto.SensorDto;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import com.jaroso.proyecto.apisensores.repositories.InfluxDBRepository;
import com.jaroso.proyecto.apisensores.repositories.SensorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    private final InfluxDBRepository influxDBRepository;
    private final SensorRepository sensorRepository;

    public SensorServiceImpl(InfluxDBRepository influxDBRepository, SensorRepository sensorRepository) {
        this.influxDBRepository = influxDBRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor saveSensor(SensorDto sensor) {
        Sensor sensorByLocation = sensorRepository.findByLocation(sensor.getLocation());
        if (sensorByLocation != null) {
            throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST,
              "Sensor already exists in that location"
            );
        }

        Sensor sensorToSave = new Sensor(
            sensor.getSensorType(),
            sensor.getLocation(),
            sensor.getLatitude(),
            sensor.getLongitude(),
            sensor.getUnit()
        );

        return sensorRepository.save(sensorToSave);
        }

    @Override
    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id)
          .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Sensor not found"
          ));
    }

    // Get sensor data from database and return values of the sensor with the location
    @Override
    public List<FluxTable> getDataBySensor(Long id) {

        Sensor sensor = sensorRepository.findById(id).orElseThrow(
          () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Sensor not found"
          )
        );

        return influxDBRepository.getDataByLocation(sensor.getLocation());
    }

    // Get all sensors of a specific type
    @Override
    public List<Sensor> getSensorsByType(SensorType sensorType) {
        return sensorRepository.findBySensorType(sensorType);
    }

    // Save data of a specific sensor
    @Override
    public SensorDataDto saveDataOfSensor(Long sensorId, SensorDataDto sensorDataDto) {
        Sensor sensor = sensorRepository.findById(sensorId).orElseThrow(
          () -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Sensor not found"
          )
        );

        influxDBRepository.saveData(sensor.getLocation(), sensorDataDto.getValue());
        return sensorDataDto;
    }

    // Get data of a specific location
    @Override
    public List<FluxTable> getDataByLocation(String location) {
        return influxDBRepository.getDataByLocation(location);
    }

    // Delete a sensor by id
    @Override
    public Sensor deleteSensorById(Long id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() ->
          new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Sensor not found"
          )
        );

        sensorRepository.deleteById(id);
        return sensor;
    }
}

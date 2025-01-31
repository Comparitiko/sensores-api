package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDto;
import com.jaroso.proyecto.apisensores.dto.SensorDto;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.models.Sensor;
import com.jaroso.proyecto.apisensores.repositories.InfluxDBRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    @Override
    public List<FluxTable> getDataBySensor(Long id) {

    }

    @Override
    public List<Sensor> getSensorsByType(SensorType sensorType) {
        return sensorRepository.findBySensorType(sensorType);
    }

    @Override
    public Sensor saveDataOfSensor(Long sensorId, SensorDataDto sensorDataDto) {
        return null;
    }

    @Override
    public List<FluxTable> getDataByLocation(String location) {
        return influxDBRepository.getDataByLocation(location);
    }
}

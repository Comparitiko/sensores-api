package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.models.Sensor;
import com.jaroso.proyecto.apisensores.repositories.InfluxDBRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    private final InfluxDBRepository influxDBRepository;
    private final SensorRepository sensorRepository;

    public SensorService(InfluxDBRepository influxDBRepository, SensorRepository sensorRepository) {
        this.influxDBRepository = influxDBRepository;
        this.sensorRepository = sensorRepository;
    }

    public void saveData(String location, double value) {
        influxDBRepository.saveData(location, value);
    }

    public List<FluxTable> getDataByLocation(String location) {
        return influxDBRepository.getDataByLocation(location);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }
}

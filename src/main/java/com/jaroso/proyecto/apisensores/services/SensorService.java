package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.repositories.InfluxDBRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    private final InfluxDBRepository influxDBRepository;

    public SensorService(InfluxDBRepository influxDBRepository) {
        this.influxDBRepository = influxDBRepository;
    }

    public void saveData(String location, double value) {
        influxDBRepository.saveData(location, value);
    }

    public List<FluxTable> getDataByLocation(String location) {
        return influxDBRepository.getDataByLocation(location);
    }
}

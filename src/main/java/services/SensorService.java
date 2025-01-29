package services;

import com.influxdb.query.FluxTable;
import repositories.InfluxDBRepository;

import java.util.List;

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

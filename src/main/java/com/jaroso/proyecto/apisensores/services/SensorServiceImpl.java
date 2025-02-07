package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDTO;
import com.jaroso.proyecto.apisensores.dto.SensorDTO;
import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import com.jaroso.proyecto.apisensores.repositories.InfluxDBRepository;
import com.jaroso.proyecto.apisensores.repositories.PlantationRepository;
import com.jaroso.proyecto.apisensores.repositories.SensorRepository;
import com.jaroso.proyecto.apisensores.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {
    private final InfluxDBRepository influxDBRepository;
    private final SensorRepository sensorRepository;
    private final PlantationRepository plantationRepository;

    public SensorServiceImpl(InfluxDBRepository influxDBRepository, SensorRepository sensorRepository, PlantationRepository plantationRepository) {
        this.influxDBRepository = influxDBRepository;
        this.sensorRepository = sensorRepository;
        this.plantationRepository = plantationRepository;
    }

    @Override
    public ResponseEntity<?> getAllSensors() {
        return ResponseEntity.ok(sensorRepository.findAll());
    }

    @Override
    public ResponseEntity<?> saveSensor(SensorDTO sensor) {
        Sensor sensorByLocation = sensorRepository.findSensorByLocation(sensor.getLocation()).orElse(null);
        if (sensorByLocation != null) {
            throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST,
              "Sensor already exists in that location"
            );
        }

        Plantation sensorPlantation = plantationRepository.findById(sensor.getPlantationId())
          .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Plantation not found"
          ));

        Sensor sensorToSave = new Sensor(
            sensor.getSensorType(),
            sensor.getLocation(),
            sensor.getLatitude(),
            sensor.getLongitude(),
            sensor.getUnit(),
            sensorPlantation
        );

        return ResponseEntity.ok(sensorRepository.save(sensorToSave));
    }

    /**
     * Method to search for a sensor by ID; if not found, it sends a response.
     * @param id
     * @return Sensor or Response
     */
    public Sensor sensorById(Long id) {
        return sensorRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sensor not found"
                )
        );
    }

    @Override
    public ResponseEntity<?> getSensorById(Long id) {
        Sensor sensor = sensorById(id);
        if (sensor == null) {
            return Response.newResponse("Sensor not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(sensor);
    }

    // Get sensor data from database and return values of the sensor with the location
    @Override
    public ResponseEntity<?> getDataBySensor(Long id) {

        Sensor sensor = sensorById(id);

        return ResponseEntity.ok(influxDBRepository.getDataByLocation(sensor.getLocation()));
    }

    // Get all sensors of a specific type
    @Override
    public ResponseEntity<?> getSensorsByType(SensorType sensorType) {
        return ResponseEntity.ok(sensorRepository.findBySensorType(sensorType));
    }

    // Save data of a specific sensor
    @Override
    public ResponseEntity<?> saveDataOfSensor(Long sensorId, SensorDataDTO sensorDataDto) {
        Sensor sensor = sensorById(sensorId);

        influxDBRepository.saveData(sensor.getLocation(), sensorDataDto.getValue(), sensor.getSensorType());
        return ResponseEntity.ok(sensorDataDto);
    }

    // Get data of a specific location
    @Override
    public ResponseEntity<?> getDataByLocation(String location) {
        return ResponseEntity.ok(influxDBRepository.getDataByLocation(location));
    }

    // Delete a sensor by id
    @Override
    public ResponseEntity<?> deleteSensorById(Long id) {

        try {
            sensorRepository.deleteById(id);
            return ResponseEntity.ok("Sensor deleted successfully");
        } catch (Exception e) {
            return Response.newResponse("Error deleting sensor, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.jaroso.proyecto.apisensores.services;

import com.influxdb.query.FluxTable;
import com.jaroso.proyecto.apisensores.dto.SensorDataDTO;
import com.jaroso.proyecto.apisensores.dto.SensorDTO;
import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import com.jaroso.proyecto.apisensores.repositories.InfluxDBRepository;
import com.jaroso.proyecto.apisensores.repositories.PlantationRepository;
import com.jaroso.proyecto.apisensores.repositories.SensorRepository;
import com.jaroso.proyecto.apisensores.responses.Response;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            return Response.newResponse(
              "Sensor already exists in that location",
              HttpStatus.BAD_REQUEST
            );
        }

        Optional<Plantation> sensorPlantation = plantationRepository.findById(sensor.getPlantationId());

        if (sensorPlantation.isEmpty()) {
            return Response.newResponse(
              "Plantation not exist",
              HttpStatus.BAD_REQUEST
            );
        }


        Sensor sensorToSave = new Sensor(
            sensor.getSensorType(),
            sensor.getLocation(),
            sensor.getLatitude(),
            sensor.getLongitude(),
            sensor.getUnit(),
            sensorPlantation.get()
        );

        try {
            sensorRepository.save(sensorToSave);

            return ResponseEntity.status(HttpStatus.CREATED).body(sensorToSave);

        } catch (OptimisticLockingFailureException e) {
            return Response.newResponse("Sensor already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return Response.newResponse("Error saving sensor, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method to search for a sensor by ID; if not found, it sends a response.
     * @param id
     * @return Sensor or Response
     */
    private Optional<Sensor> sensorById(Long id) {
        return sensorRepository.findById(id);
    }

    @Override
    public ResponseEntity<?> getSensorById(Long id) {
        Optional<Sensor> sensor = sensorById(id);

        if (sensor.isEmpty()) {
            return Response.newResponse("Sensor not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(sensor);
    }

    // Get sensor data from database and return values of the sensor with the location
    @Override
    public ResponseEntity<?> getDataBySensor(Long id) {

        Optional<Sensor> sensor = sensorById(id);

        if (sensor.isEmpty()) {
            return Response.newResponse("Sensor not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(influxDBRepository.getDataByLocation(sensor.get().getLocation()));
    }

    // Get all sensors of a specific type
    @Override
    public ResponseEntity<?> getSensorsByType(SensorType sensorType) {
        return ResponseEntity.ok(sensorRepository.findBySensorType(sensorType));
    }

    // Save data of a specific sensor
    @Override
    public ResponseEntity<?> saveDataOfSensor(Long sensorId, SensorDataDTO sensorDataDto) {
        Optional<Sensor> sensor = sensorById(sensorId);

        if (sensor.isEmpty()) {
            return Response.newResponse("Sensor not found", HttpStatus.NOT_FOUND);
        }

        influxDBRepository.saveData(sensor.get().getLocation(), sensorDataDto.getValue(), sensor.get().getSensorType());

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
            return Response.newResponse("Sensor deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return Response.newResponse("Error deleting sensor, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

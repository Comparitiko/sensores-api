package com.jaroso.proyecto.apisensores.repositories;

import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.entities.Sensor;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.enums.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findSensorsBySensor_type(SensorType sensor_type);

    List<Sensor> findSensorsByLocation(String location);

    List<Sensor> findSensorsByPlantation(Plantation plantation);

    List<Sensor> findSensorsByPlantationAndSensor_type(Plantation plantation, SensorType sensor_type);

    List<Sensor> findSensorsByPlantationAndLocation(Plantation plantation, String location);

    List<Sensor> findSensorsByUnit(Unit unit);

    List<Sensor> findSensorByCreatedAtBetween(LocalDateTime createdAtAfter, LocalDateTime createdAtBefore);
}

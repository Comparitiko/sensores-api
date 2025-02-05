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
    List<Sensor> findBySensorType(SensorType sensorType);

    List<Sensor> findByLocation(String location);

    List<Sensor> findByPlantation(Plantation plantation);

    List<Sensor> findByPlantationAndSensorType(Plantation plantation, SensorType sensorType);

    List<Sensor> findByPlantationAndLocation(Plantation plantation, String location);

    List<Sensor> findByUnit(Unit unit);

    List<Sensor> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}

package com.jaroso.proyecto.apisensores.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaroso.proyecto.apisensores.enums.SensorType;
import com.jaroso.proyecto.apisensores.enums.Unit;
import com.jaroso.proyecto.apisensores.entities.Plantation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SensorType sensor_type;

    private String location;

    private Double latitude;

    private Double longitude;

    private Unit unit;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(mapedBy = "sensors")
    private Plantation plantation;

    //CONSTRUCTORS
    public Sensor(SensorType sensor_type, String location, Double latitude, Double longitude, Unit unit, Plantation plantation) {
        this.sensor_type = sensor_type;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.unit = unit;
        this.plantation = plantation;
    }

    //METHODS

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }



}

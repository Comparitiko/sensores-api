package com.jaroso.proyecto.apisensores.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "plantations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plantation {

    // ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ubication;

    private String country;

    private String province;

    private String city;

    private String coordinates;

    @Column(name = "plantation_type")
    private String plantationType;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sensor> sensors;

    //CONSTRUCTORS
    public Plantation(String name, String ubication, String country, String province, String city, String coordinates, String plantationType) {
        this.name = name;
        this.ubication = ubication;
        this.country = country;
        this.province = province;
        this.city = city;
        this.coordinates = coordinates;
        this.plantationType = plantationType;
    }

    public Plantation(Long id, String name, String ubication, String country, String province, String city, String coordinates, String plantationType) {
        this.id = id;
        this.name = name;
        this.ubication = ubication;
        this.country = country;
        this.province = province;
        this.city = city;
        this.coordinates = coordinates;
        this.plantationType = plantationType;
    }

    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getPlantationType() {
        return plantationType;
    }

    public void setPlantationType(String plantationType) {
        this.plantationType = plantationType;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    //ToString
    @Override
    public String toString() {
        return "Plantation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ubication='" + ubication + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", plantationType='" + plantationType + '\'' +
                ", sensors=" + sensors +
                '}';
    }
}

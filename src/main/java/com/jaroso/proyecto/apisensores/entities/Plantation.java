package com.jaroso.proyecto.apisensores.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    //CONSTRUCTORS
    public Plantation(String name, String ubication, String country, String province, String city,
                      String coordinates, String plantationType, User user) {
        this.name = name;
        this.ubication = ubication;
        this.country = country;
        this.province = province;
        this.city = city;
        this.coordinates = coordinates;
        this.plantationType = plantationType;
        this.user = user;
    }

    public Plantation(Long id, String name, String ubication, String country, String province,
                      String city, String coordinates, String plantationType, User user) {
        this.id = id;
        this.name = name;
        this.ubication = ubication;
        this.country = country;
        this.province = province;
        this.city = city;
        this.coordinates = coordinates;
        this.plantationType = plantationType;
        this.user = user;
    }
}

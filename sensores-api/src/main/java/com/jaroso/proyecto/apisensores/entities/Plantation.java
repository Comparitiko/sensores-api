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

    @Column(unique = true)
    private String name;

    private String ubication;

    private String country;

    private String province;

    private String city;

    @Column(unique = true)
    private String coordinates;

    @Column(name = "plantation_type")
    private String plantationType;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sensor> sensors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}

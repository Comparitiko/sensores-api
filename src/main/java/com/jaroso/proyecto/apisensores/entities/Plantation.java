package com.jaroso.proyecto.apisensores.entities;

import jakarta.persistence.*;

@Entity
public class Plantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ubication;

    @Column(name = "plantation_type")
    private String plantationType;


}

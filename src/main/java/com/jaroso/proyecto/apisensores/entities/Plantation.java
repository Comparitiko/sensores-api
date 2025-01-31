package com.jaroso.proyecto.apisensores.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plantatios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plantation {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ubication;

    private String coordinates;

    @Column(name = "plantation_type")
    private String plantationType;

}

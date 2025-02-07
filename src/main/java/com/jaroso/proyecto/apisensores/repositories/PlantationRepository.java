package com.jaroso.proyecto.apisensores.repositories;

import com.jaroso.proyecto.apisensores.entities.Plantation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {

    /*Método para buscar por tipo de producción*/
    List<Plantation> findByPlantationType(String plantationType);

    Optional<Plantation> findPlantationById(Long userId);

    Optional<Plantation> findByName(String name);

    Optional<Plantation> findByCoordinates(String coordinates);

    @NotNull Optional<Plantation> findById(@NotNull Long id);

}

package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.entities.Plantation;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlantationService {

    List<Plantation> getAllPlantations();

    Optional<Plantation> getPlantationById(@NotNull Long id);

    Optional<Plantation> getPlantationByName(String name);

    List<Plantation> getPlantationsByTypeOfProduction(String typeOfProduction);

    Plantation savePlantation(Plantation plantation);

    void deletePlantation(Long id);
}

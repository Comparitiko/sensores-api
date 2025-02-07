package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.PlantationDTO;
import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlantationService {

    ResponseEntity<?> getAllPlantations();

    ResponseEntity<?> getPlantationById(@NotNull Long id);

    ResponseEntity<?> getPlantationByName(String name);

    ResponseEntity<?> getPlantationsByTypeOfProduction(String typeOfProduction);

    ResponseEntity<?> savePlantation(PlantationDTO plantationDTO);

    ResponseEntity<?> deletePlantation(Long id);
}

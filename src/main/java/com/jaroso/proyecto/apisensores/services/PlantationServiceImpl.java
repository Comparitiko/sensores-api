package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.entities.Plantation;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantationServiceImpl implements PlantationService {

    private final PlantationRepository plantationRepository;

    public PlantationServiceImpl(PlantationRepository plantationRepository) {
        this.plantationRepository = plantationRepository;
    }

    @Override
    public List<Plantation> getAllPlantations() {
        return plantationRepository.findAll();
    }

    @Override
    public Optional<Plantation> getPlantationById(@NotNull Long id) {
        return plantationRepository.findById(id);
    }

    @Override
    public Optional<Plantation> getPlantationByName(String name) {
        return plantationRepository.findByName(name);
    }

    @Override
    public List<Plantation> getPlantationsByTypeOfProduction(String typeOfProduction) {
        return plantationRepository.findByTypeOfProduction(typeOfProduction);
    }

    @Override
    public Plantation savePlantation(Plantation plantation) {
        return plantationRepository.save(plantation);
    }

    @Override
    public void deletePlantation(Long id) {
        plantationRepository.deleteById(id);

    }
}

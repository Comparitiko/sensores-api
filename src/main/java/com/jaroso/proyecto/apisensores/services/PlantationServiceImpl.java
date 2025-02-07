package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.PlantationDTO;
import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.repositories.PlantationRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return plantationRepository.findByPlantationType(typeOfProduction);
    }

    @Override
    public Plantation savePlantation(PlantationDTO plantationDTO) {

        Plantation checkPlantation = plantationRepository.findByCoordinates(plantationDTO.getCoordinates()).orElse(null);
        if(checkPlantation != null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Plantation already exists."
            );
        }

        Plantation plantation = new Plantation();
        plantation.setName(plantationDTO.getName());
        plantation.setUbication(plantationDTO.getName());
        plantation.setCountry(plantationDTO.getCountry());
        plantation.setProvince(plantationDTO.getProvince());
        plantation.setCity(plantationDTO.getCity());
        plantation.setCoordinates(plantationDTO.getCoordinates());
        plantation.setPlantationType(plantationDTO.getPlantationType());

        return plantationRepository.save(plantation);
    }

    @Override
    public void deletePlantation(Long id) {

        plantationRepository.deleteById(id);

    }
}

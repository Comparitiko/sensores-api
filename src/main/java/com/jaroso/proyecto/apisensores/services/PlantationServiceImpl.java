package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.PlantationDTO;
import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.repositories.PlantationRepository;
import com.jaroso.proyecto.apisensores.responses.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAllPlantations() {
        return ResponseEntity.ok(plantationRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getPlantationById(@NotNull Long id) {
        Plantation plantation = plantationRepository.findById(id).orElse(null);

        if (plantation == null) {
            return Response.newResponse("Plantation not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(plantation);
    }

    @Override
    public ResponseEntity<?> getPlantationByName(String name) {
        Plantation plantation = plantationRepository.findPlantationByName(name).orElse(null);

        if (plantation == null) {
            return Response.newResponse("Plantation not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(plantation);
    }

    @Override
    public ResponseEntity<?> getPlantationsByTypeOfProduction(String typeOfProduction) {
        return ResponseEntity.ok(plantationRepository.findByPlantationType(typeOfProduction));
    }

    @Override
    public ResponseEntity<?> savePlantation(PlantationDTO plantationDTO) {

        Optional<Plantation> checkPlantation =
          plantationRepository.findByCoordinatesOrName(plantationDTO.getCoordinates(), plantationDTO.getName());
        if(checkPlantation.isPresent()){
            Response.newResponse("Plantation already exists", HttpStatus.BAD_REQUEST);
        }

        Plantation plantation = new Plantation();
        plantation.setName(plantationDTO.getName());
        plantation.setUbication(plantationDTO.getName());
        plantation.setCountry(plantationDTO.getCountry());
        plantation.setProvince(plantationDTO.getProvince());
        plantation.setCity(plantationDTO.getCity());
        plantation.setCoordinates(plantationDTO.getCoordinates());
        plantation.setPlantationType(plantationDTO.getPlantationType());

        try {
            plantationRepository.save(plantation);

            return ResponseEntity.ok(plantation);
        } catch (OptimisticLockingFailureException e) {
            return Response.newResponse("Plantation already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return Response.newResponse("Error saving plantation, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deletePlantation(Long id) {

        try {
            plantationRepository.deleteById(id);
            return Response.newResponse("Plantation deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return Response.newResponse("Error deleting plantation, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

package com.jaroso.proyecto.apisensores.controllers;

import com.jaroso.proyecto.apisensores.entities.Plantation;
import com.jaroso.proyecto.apisensores.services.PlantationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plantations")
public class PlantationController {

    private final PlantationService plantationService;

    public PlantationController(PlantationService plantationService) {
        this.plantationService = plantationService;
    }

    /**
     * Get all plantations
     * @return List<Plantation>
     */
    @GetMapping
    public List<Plantation> getAllPlantations() {
        return plantationService.getAllPlantations();
    }

    /**
     * Get plantation by id
     * @param id Long
     * @return Optional<Plantation>
     */
    @GetMapping("/{id}")
    public Optional<Plantation> getPlantationById(@PathVariable @NotNull Long id) {
        return plantationService.getPlantationById(id);
    }

    /**
     * Get plantation by name
     * @param name String
     * @return Optional<Plantation>
     */
    @GetMapping("/name/{name}")
    public Optional<Plantation> getPlantationByName(@PathVariable String name) {
        return plantationService.getPlantationByName(name);
    }

    /**
     * Get plantations by type of production
     * @param typeOfProduction String
     * @return List<Plantation>
     */
    @GetMapping("/type/{typeOfProduction}")
    public List<Plantation> getPlantationsByTypeOfProduction(@PathVariable String typeOfProduction) {
        return plantationService.getPlantationsByTypeOfProduction(typeOfProduction);
    }

    /**
     * Save plantation
     * @param plantation Plantation
     * @return Plantation
     */
    @PostMapping
    public Plantation savePlantation(@RequestBody Plantation plantation) {
        return plantationService.savePlantation(plantation);
    }

    /**
     * Delete plantation by id
     * @param id Long
     */
    @DeleteMapping("/{id}")
    public void deletePlantation(@PathVariable @NotNull Long id) {
        plantationService.deletePlantation(id);
    }


}

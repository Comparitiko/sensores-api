package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.entities.Plantation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlantationService {

    public Plantation getPlantation(Long id);
    public List<Plantation> getAllPlantations();
    public void deletePlantationById(Long id);
    public void savePlantation(Plantation plantation);

}

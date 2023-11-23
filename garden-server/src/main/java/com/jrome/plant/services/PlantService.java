package com.jrome.plant.services;

import com.jrome.plant.entities.Plant;
import com.jrome.plant.repositories.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;

    public Optional<Plant> findPlantById(Long id) {
        //TODO: If there are no plants in DB , don't return null value
        return plantRepository.findById(id);
    }

    public List<Plant> findAllPlants() {
        //TODO: If there are no plants in DB , don't return null value
        return plantRepository.findAll();
    }

    public void savePlant(Plant plant) {
        plantRepository.save(plant);
    }

    public void updatePlant(Plant plant, Long id) {


        if (!plantRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        } else {
            plant.setId(id);
            plantRepository.save(plant);
        }

    }
    public void deletePlantById(Long id) {
        plantRepository.deleteById(id);
    }
}

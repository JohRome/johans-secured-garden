package com.jrome.services;

import com.jrome.entities.Plant;
import com.jrome.repositories.PlantRepository;
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
        return plantRepository.findById(id);
    }

    public List<Plant> findAllPlants() {
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

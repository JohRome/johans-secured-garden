package com.jrome.controllers;

import com.jrome.entities.Plant;
import com.jrome.services.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;


    @GetMapping("/{id}")
    public Optional<Plant> findPlantById(@PathVariable Long id) {
        return plantService.findPlantById(id);
    }

    @GetMapping("/")
    public List<Plant> findAllPlants() {
        return plantService.findAllPlants();
    }

    @PostMapping("/")
    public void savePlant(@Valid @RequestBody Plant plant) {
        plantService.savePlant(plant);

    }

    @PutMapping("/{id}")
    public void updatePlant(@RequestBody Plant plant, @PathVariable Long id) {
        plantService.updatePlant(plant, id);

    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) {
        plantService.deletePlantById(id);
    }
}

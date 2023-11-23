package com.jrome.plant.controllers;

import com.jrome.plant.entities.Plant;
import com.jrome.plant.services.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/plants")
@RequiredArgsConstructor
public class PlantController {
    //TODO: Return ResponseEntity<> and proper HttpResponses for all methods

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

    @PreAuthorize("hasRole('GARDEN_MASTER')")
    @PutMapping("/{id}")
    public void updatePlant(@RequestBody Plant plant, @PathVariable Long id) {
        //TODO: If plant with id dosn't exists, dont return Unauthorized
        plantService.updatePlant(plant, id);
    }

    @PreAuthorize("hasRole('GARDEN_MASTER')")
    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) {
        //TODO: If plant with id dosn't exists, dont return 200 OK
        plantService.deletePlantById(id);
    }
}

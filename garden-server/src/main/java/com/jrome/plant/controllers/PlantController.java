package com.jrome.plant.controllers;

import com.jrome.plant.entities.Plant;
import com.jrome.plant.services.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping("/{id}")
    public ResponseEntity<Plant> findPlantById(@PathVariable Long id) {

        return new ResponseEntity<>(plantService.findPlantById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Plant>> findAllPlants() {

        return new ResponseEntity<>(plantService.findAllPlants(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Plant> savePlant(@Valid @RequestBody Plant plant) {

        Plant createdPlant = plantService.savePlant(plant);

        return new ResponseEntity<>(createdPlant, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('GARDEN_MASTER')")
    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant, @PathVariable Long id) {

        Plant updatedPlant = plantService.updatePlant(plant,id);

        return new ResponseEntity<>(updatedPlant, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('GARDEN_MASTER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlant(@PathVariable Long id) {

        plantService.deletePlantById(id);
    }
}

package com.jrome.repositories;

import com.jrome.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    Optional<Plant> findPlantById(Long id);

}

package com.example.newProject.Repositories.BasicRepos;

import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.ModelEntities.LuxuryAttributesOfHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuxuryAttributeRepo extends JpaRepository<LuxuryAttributesOfHouse,Long> {

    LuxuryAttributesOfHouse findByHouse(House house);
}

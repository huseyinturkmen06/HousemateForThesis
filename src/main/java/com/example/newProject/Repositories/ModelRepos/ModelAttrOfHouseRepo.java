package com.example.newProject.Repositories.ModelRepos;

import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelAttrOfHouseRepo extends JpaRepository<ModelAttributesOfHouse,Long> {

    ModelAttributesOfHouse findByHouse(House house);
}

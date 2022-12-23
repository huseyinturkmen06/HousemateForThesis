package com.example.newProject.Repositories.ModelRepos;

import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelAttrOfHouseOwnerRepo extends JpaRepository<ModelAttributesOfHouseOwner,Long> {

    ModelAttributesOfHouseOwner findByHouseOwner(HouseOwner houseOwner);
}

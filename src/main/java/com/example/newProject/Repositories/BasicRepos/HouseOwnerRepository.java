package com.example.newProject.Repositories.BasicRepos;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseOwnerRepository extends JpaRepository<HouseOwner,Long> {

    HouseOwner findHouseOwnerByOwnerUsername(String username);

    List<HouseOwner> findAllByHouse(House house);

}
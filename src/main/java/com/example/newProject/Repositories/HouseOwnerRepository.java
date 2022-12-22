package com.example.newProject.Repositories;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseOwnerRepository extends JpaRepository<HouseOwner,Long> {

    HouseOwner findHouseOwnerByOwnerUsername(String username);

}
package com.example.newProject.Repositories;
import com.example.newProject.Entities.BasicEntities.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House,Long> {



}
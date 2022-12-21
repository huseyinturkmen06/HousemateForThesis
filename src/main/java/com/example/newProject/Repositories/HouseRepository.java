package com.example.newProject.Repositories;
import com.example.newProject.Entities.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {



}
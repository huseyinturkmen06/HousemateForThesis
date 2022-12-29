package com.example.newProject.Repositories.BasicRepos;
import com.example.newProject.Entities.BasicEntities.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {



    //model of house class ından belirli class lara ait olan kaytıların house id larını çek ve listeye at
    //sonra burada o listeden gelen tüm house Id leri çek



}
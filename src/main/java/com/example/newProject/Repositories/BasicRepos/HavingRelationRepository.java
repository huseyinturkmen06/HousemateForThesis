package com.example.newProject.Repositories.BasicRepos;

import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HavingRelationRepository extends JpaRepository<HouseCustomer,Long> {

    List<HouseCustomer> findHavingRelationsByCustomer(Customer customer);
    List<HouseCustomer> findHavingRelationsByHouse(House house);
}

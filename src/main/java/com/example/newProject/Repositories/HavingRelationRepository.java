package com.example.newProject.Repositories;

import com.example.newProject.Entities.Customer;
import com.example.newProject.Entities.House;
import com.example.newProject.Entities.HouseCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HavingRelationRepository extends JpaRepository<HouseCustomer,Long> {

    List<HouseCustomer> findHavingRelationsByCustomer(Customer customer);
    List<HouseCustomer> findHavingRelationsByHouse(House house);
}

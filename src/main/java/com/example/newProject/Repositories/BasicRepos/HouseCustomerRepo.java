package com.example.newProject.Repositories.BasicRepos;

import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseCustomerRepo extends JpaRepository<HouseCustomer,Long> {

    HouseCustomer findByHouseAndCustomer(House house, Customer customer);

}

package com.example.newProject.Services;


import com.example.newProject.Entities.Customer;
import com.example.newProject.Entities.House;
import com.example.newProject.Entities.HouseCustomer;
import com.example.newProject.Repositories.CustomerRepository;
import com.example.newProject.Repositories.HavingRelationRepository;
import com.example.newProject.Repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    HouseRepository houseRepository;
    HavingRelationRepository havingRelationRepository;
    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(HouseRepository houseRepository,HavingRelationRepository havingRelationRepository,CustomerRepository customerRepository) {
        this.houseRepository = houseRepository;
        this.havingRelationRepository = havingRelationRepository;
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomersOfOneHouse(int id){
        House house = houseRepository.findById(id).orElse(null);
        List<HouseCustomer> relaions=havingRelationRepository.findHavingRelationsByHouse(house);
        //fe found all relations of one house
        List<Customer> allCustomersOfOneHouse=new ArrayList<>();
        for(HouseCustomer relation: relaions){
            allCustomersOfOneHouse.add(relation.getCustomer());
        }
        //we found all houses of one customer

        return allCustomersOfOneHouse;
    }


    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveOneCustomer(Customer customer){
        return customerRepository.save(customer);
    }



}

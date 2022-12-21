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


    //1 tane house çek ve onun içinde olduğu tüm ilişkilerini bul
    //sonra bu ilişkilerin her birisinin customerını bul ve listeye ekle
    //sonuçta bir evin tüm customerlarını bulmuş oluruz
    public List<Customer> getAllCustomersOfOneHouse(Long id){
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

    //get all customers in db
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveOneCustomer(Customer customer){
        return customerRepository.save(customer);
    }



}

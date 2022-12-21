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
public class HouseService {
    HouseRepository houseRepository;
    HavingRelationRepository havingRelationRepository;
    CustomerRepository customerRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository,HavingRelationRepository havingRelationRepository,CustomerRepository customerRepository) {
        this.houseRepository = houseRepository;
        this.havingRelationRepository = havingRelationRepository;
        this.customerRepository = customerRepository;
    }

    public List<HouseCustomer> getRalationsOfOneCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        return havingRelationRepository.findHavingRelationsByCustomer(customer);
    }



    //Get all houses of one customer
    public List<House> getAllHousesOfOneCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        List<HouseCustomer> relaions=havingRelationRepository.findHavingRelationsByCustomer(customer);
        //fe found all relations of one customer
        List<House> allHousesOfOneUser=new ArrayList<>();
        for(HouseCustomer relation: relaions){
            allHousesOfOneUser.add(relation.getHouse());
        }
        //we found all houses of one customer

        return allHousesOfOneUser;
    }


    //Get all houses
    public List<House> getAllHouses(){
        return houseRepository.findAll();
    }


    //house id yi şimdilik path variable ilealıyoruz ama ilerde sto ile alıncak
    public House getOneHouseById(Long houseId){
        return houseRepository.findById(houseId).orElse(null);
    }














}

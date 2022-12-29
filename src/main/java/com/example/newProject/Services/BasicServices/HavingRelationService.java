package com.example.newProject.Services.BasicServices;

import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.BasicRepos.HavingRelationRepository;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HavingRelationService {

    private HavingRelationRepository havingRelationRepository;
    private HouseRepository houseRepository;
    private HouseOwnerRepository houseOwnerRepository;
    private CustomerRepository customerRepository;
    private HouseService houseService;
    private HouseOwnerService houseOwnerService;
    private CustomerService customerService;

    @Autowired
    //constructor including required beans
    public HavingRelationService(HavingRelationRepository havingRelationRepository, HouseRepository houseRepository, HouseOwnerRepository houseOwnerRepository, CustomerRepository customerRepository, HouseService houseService, HouseOwnerService houseOwnerService, CustomerService customerService) {
        this.havingRelationRepository = havingRelationRepository;
        this.houseRepository = houseRepository;
        this.houseOwnerRepository = houseOwnerRepository;
        this.customerRepository = customerRepository;
        this.houseService = houseService;
        this.houseOwnerService = houseOwnerService;
        this.customerService = customerService;
    }








}

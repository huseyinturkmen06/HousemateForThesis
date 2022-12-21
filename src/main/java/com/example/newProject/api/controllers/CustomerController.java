package com.example.newProject.api.controllers;

import com.example.newProject.Entities.Customer;
import com.example.newProject.Entities.House;
import com.example.newProject.Repositories.CustomerRepository;
import com.example.newProject.Services.CustomerService;
import com.example.newProject.Services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private HouseService houseService;
    private CustomerService customerService;


    @Autowired
    public CustomerController(HouseService houseService,CustomerService customerService) {
        this.houseService = houseService;
        this.customerService=customerService;
    }



    @GetMapping("/getAllCustomersOfOneHouse/{houseId}")
    public List<Customer> getAllCustomersOfOneHouse(@PathVariable Long houseId){
        return customerService.getAllCustomersOfOneHouse(houseId);
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/saveOneCustomer")
    public Customer saveOneCustomer(@RequestBody Customer customer){
        return customerService.saveOneCustomer(customer);
    }





}

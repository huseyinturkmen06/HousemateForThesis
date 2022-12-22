package com.example.newProject.api.controllers;

import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Services.CustomerService;
import com.example.newProject.Services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//burassının metotlarını da tamamla


@RestController
@RequestMapping("/houses")
public class HouseController {

    private HouseService houseService;
    private CustomerService customerService;

    @Autowired
    public HouseController(HouseService houseService,CustomerService customerService) {
        this.houseService = houseService;
        this.customerService=customerService;
    }

    @GetMapping("/getAllHousesOfOneCustomer/{customerId}")
    public List<House> getAllHousesOfOneCustomer(@PathVariable Long customerId){
        return houseService.getAllHousesOfOneCustomer(customerId);
    }

    //getAllHouses

    //getOneHouseById

    //getHousesByAdress ----->House lara adreslerini belli eden attribuler ler eklenmeli

    //saveOneHouse

    //updateOneHouse











}

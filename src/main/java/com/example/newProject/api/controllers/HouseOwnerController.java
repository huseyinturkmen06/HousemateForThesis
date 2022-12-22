package com.example.newProject.api.controllers;


import com.example.newProject.DTOs.CustomerRegisterDto;
import com.example.newProject.DTOs.CustomerUpdateDto;
import com.example.newProject.DTOs.HouseOwnerRegisterDto;
import com.example.newProject.DTOs.HouseOwnerUpdateDto;
import com.example.newProject.Entities.Customer;
import com.example.newProject.Entities.HouseOwner;
import com.example.newProject.Services.HouseOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseOwners")
public class HouseOwnerController {

    private HouseOwnerService houseOwnerService;
    @Autowired
    public HouseOwnerController(HouseOwnerService houseOwnerService) {
        this.houseOwnerService = houseOwnerService;
    }


    //metodları public yaz yoksa default hali ile dış paketlerden erişemiyoruz


    //getAllOwners
    @GetMapping("/getAllHouseOwners")
    public List<HouseOwner> getAllHouseOwners(){
        return houseOwnerService.getAllOwners();
    }

    //getOneOwnerById
    @GetMapping("/getOneHouseOwnerById/{houseOwnerId}")
    public HouseOwner getOneHouseOwnerById(@PathVariable Long houseOwnerId) {
        return houseOwnerService.getOneOwnerByOwnerId(houseOwnerId);
    }

    //getOneOwnerByUserName
    @GetMapping("/getOneHouseOwnerByUsername/{houseOwnerUsername}")
    public HouseOwner getOneCustomerByUsername(@PathVariable String houseOwnerUsername){
        return houseOwnerService.getOneOwnerByOwnerUserName(houseOwnerUsername);
    }

    //saveOneOwner
    @PostMapping("/saveOneHouseOwner")
    public HouseOwner saveOneHouseOwner(@RequestBody HouseOwnerRegisterDto houseOwnerRegisterDto){
        return houseOwnerService.saveOneHouseOwner(houseOwnerRegisterDto);
    }

    //updateOneOwner
    @PutMapping("/updateOneHouseOwner")
    public HouseOwner updateOneCustomer(@RequestBody HouseOwnerUpdateDto houseOwnerUpdateDto){
        return houseOwnerService.updateOneHouseOwner(houseOwnerUpdateDto);
    }




    //updatePasswordOfOneOwner




}

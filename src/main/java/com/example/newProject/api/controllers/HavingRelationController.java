package com.example.newProject.api.controllers;


import com.example.newProject.DTOs.BasicDtos.CreateRelationDto;
import com.example.newProject.DTOs.BasicDtos.DeleteRelationDto;
import com.example.newProject.DTOs.BasicDtos.LikeControlDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Services.BasicServices.CustomerService;
import com.example.newProject.Services.BasicServices.HouseCustomerService;
import com.example.newProject.Services.BasicServices.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//aslında buraya pek ihtiyaç kalmayabilir
//çünkü bu entity nin metodları (service kısmındaki) diğer classlar içine dağılabilir

@RestController
@RequestMapping("/relations")
@CrossOrigin
public class HavingRelationController {

    private HouseService houseService;
    private HouseCustomerService houseCustomerService;
    private CustomerService customerService;
    @Autowired
    public HavingRelationController(HouseService houseService,HouseCustomerService houseCustomerService,CustomerService customerService) {
        this.houseService = houseService;
        this.houseCustomerService=houseCustomerService;
        this.customerService=customerService;
    }

//    @GetMapping("/getRalationsOfOneCustomer/{id}")
//    public List<HouseCustomer> getRalationsOfOneCustomer(@PathVariable Long id){
//        return houseService.getRalationsOfOneCustomer(id);
//    }




    @GetMapping("/getAllHousesOfOneCustomer/{customerId}")
    public List<House> getAllHousesOfOneCustomer(@PathVariable Long customerId){
        return houseCustomerService.getAllHousesOfOneCustomer(customerId);
    }


    //özel metod, şimdilik lazım değil
    @GetMapping("/getAllCustomersOfOneHouse/{houseId}") //evimi beğenen bütün kullanıcılar
    public List<Customer> getAllCustomersOfOneHouse(@PathVariable Long houseId){
        return houseCustomerService.getAllCustomersOfOneHouse(houseId);
    }


    @PostMapping("/createOneRelation")
    public HouseCustomer createOneRelation(@RequestBody CreateRelationDto createRelationDto){
        return houseCustomerService.createOneRelation(createRelationDto);
    }


    @PostMapping("/likeControl")
    public Boolean likeControl(@RequestBody LikeControlDto likeControlDto){
        return houseCustomerService.likeControl(likeControlDto);
    }

    @DeleteMapping("/deleteRelation")
    public HouseCustomer deleteOneRelation(@RequestBody DeleteRelationDto deleteRelationDto){
        return houseCustomerService.deleteOneRelation(deleteRelationDto);
    }




}

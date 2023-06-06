package com.example.newProject.api.controllers;

import com.example.newProject.DTOs.BasicDtos.HouseSaveDto;
import com.example.newProject.DTOs.BasicDtos.HouseUpdateDto;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Services.BasicServices.CustomerService;
import com.example.newProject.Services.BasicServices.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    //getAllHouses
    @GetMapping("/getAllHouses")
    public List<House> getAllHouses(){
        return houseService.getAllHouses();
    }

    //getOneHouseById
    public House getOneHouseById(Long houseId){
        return houseService.getOneHouseById(houseId);
    }

    //getHousesByAdress ----->House lara adreslerini belli eden attribuler ler eklenmeli

    //saveOneHouse
    @PostMapping("/saveOneHouse")
    public House saveOneHouse(@RequestBody HouseSaveDto houseSaveDto){
        return houseService.saveOneHouse(houseSaveDto);
    }
    //bir ev kaydederken DTO dan Luxury Of HOuse içinde bilgi alıyoruz
    //ve luxury of house tablosuna da ekleme yapıyoruz


    @GetMapping("/getOneHouseById/{houseId}")
    public House getOneHouseByHouseId(@PathVariable Long houseId){
        return houseService.getOneHouseById(houseId);
    }




    //updateOneHouse
    //bunu service içinde yazdım ama bazı kontroller yapıp buraya eklicem


    @GetMapping("/getHousesByClass/{classOfHouse}")
    public List<House> getHousesByClass(@PathVariable String classOfHouse){
        return houseService.getHousesByClass(classOfHouse);
    }

    @PutMapping("/updateOneHouseByHouseId/{houseId}")
    public House updateOneHouseByHouseId(@PathVariable Long houseId,@RequestBody HouseUpdateDto houseResponse){
        return houseService.updateOneHouseByHouseId(houseId,houseResponse);
    }











}

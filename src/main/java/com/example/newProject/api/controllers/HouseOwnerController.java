package com.example.newProject.api.controllers;


import com.example.newProject.DTOs.BasicDtos.HouseOwnerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.HouseOwnerUpdateDto;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Services.BasicServices.HouseOwnerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseOwners")
public class HouseOwnerController {

    private HouseOwnerService houseOwnerService;
    private ModelAttrOfHouseService modelAttrOfHouseService;
    @Autowired
    public HouseOwnerController(HouseOwnerService houseOwnerService
    ,ModelAttrOfHouseService modelAttrOfHouseService) {
        this.houseOwnerService = houseOwnerService;
        this.modelAttrOfHouseService=modelAttrOfHouseService;
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
        houseOwnerService.saveOneHouseOwner(houseOwnerRegisterDto);
        //önce kullanıcıyı kaydet
        modelAttrOfHouseService.setAttributesOfOneHouseByOwners(houseOwnerRegisterDto.getHouseId());
        //sonra o kullanıcının özelliklerini de göz önüne alarak house özelliğini kaydet
        return null;
        //üstteki sıra değimesin diye burada bi şey döndürmedim.
        //burasının sonuçlarını db den bakabiliriz
    }

    //updateOneOwner
    @PutMapping("/updateOneHouseOwner")
    public HouseOwner updateOneCustomer(@RequestBody HouseOwnerUpdateDto houseOwnerUpdateDto){
        return houseOwnerService.updateOneHouseOwner(houseOwnerUpdateDto);
    }




    //updatePasswordOfOneOwner




}

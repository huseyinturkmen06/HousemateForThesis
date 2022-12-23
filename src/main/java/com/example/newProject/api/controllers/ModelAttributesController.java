package com.example.newProject.api.controllers;

import com.example.newProject.DTOs.ModelDtos.ModelAttrOfCustomerDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfHouseOwnerDto;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfCustomer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Services.ModelServices.ModelAttrOfCustomerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfHouseOwnerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/models")
public class ModelAttributesController {

    private ModelAttrOfCustomerService modelAttrOfCustomerService;
    private ModelAttrOfHouseService modelAttrOfHouseService;
    private ModelAttrOfHouseOwnerService modelAttrOfHouseOwnerService;

    @Autowired
    public ModelAttributesController(ModelAttrOfCustomerService modelAttrOfCustomerService,
                                     ModelAttrOfHouseService modelAttrOfHouseService,
                                     ModelAttrOfHouseOwnerService modelAttrOfHouseOwnerService) {
        this.modelAttrOfCustomerService = modelAttrOfCustomerService;
        this.modelAttrOfHouseService = modelAttrOfHouseService;
        this.modelAttrOfHouseOwnerService = modelAttrOfHouseOwnerService;
    }


    //saveOneHouseOwnerAttribute
    @PostMapping("/saveOneHouseOwnerAttribute")
    public ModelAttributesOfHouseOwner saveOneHouseOwnerAttribute(
            @RequestBody ModelAttrOfHouseOwnerDto modelAttrOfHouseOwnerDto){
        return modelAttrOfHouseOwnerService.saveOneHouseOwnerAttribute(modelAttrOfHouseOwnerDto);
    }


    //saveOneModelAttrOfCustomer
    @PostMapping("/saveOneModelAttrOfCustomer")
    public ModelAttributesOfCustomer saveOneCustomerAttribute(
            @RequestBody ModelAttrOfCustomerDto modelAttrOfCustomerDto){
        return modelAttrOfCustomerService.saveOneCustomerAttribute(modelAttrOfCustomerDto);
    }

    //saveOneHouseAttribute
    //house attributeleri bir post ile değil,
    //kendisinde kalan houseowner ların attributeleri ortalaması ile olacak

    //bu  anketlerin update işlemleri daha sonra yazılacak





}

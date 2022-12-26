package com.example.newProject.api.controllers;

import com.example.newProject.DTOs.ModelDtos.ModelAttrOfCustomerDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfHouseOwnerDto;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfCustomer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Services.BasicServices.HouseOwnerService;
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
    private HouseOwnerService houseOwnerService;

    @Autowired
    public ModelAttributesController(ModelAttrOfCustomerService modelAttrOfCustomerService,
                                     ModelAttrOfHouseService modelAttrOfHouseService,
                                     ModelAttrOfHouseOwnerService modelAttrOfHouseOwnerService,
                                     HouseOwnerService houseOwnerService) {
        this.modelAttrOfCustomerService = modelAttrOfCustomerService;
        this.modelAttrOfHouseService = modelAttrOfHouseService;
        this.modelAttrOfHouseOwnerService = modelAttrOfHouseOwnerService;
        this.houseOwnerService=houseOwnerService;
    }



//bunlar birden çok kayıt girmiş olursa post ların yanında putları da girr



    //saveOrUpdateOneHouseOwnerAttribute
    @PostMapping("/saveOrUpdateOneHouseOwnerAttribute")
    public ModelAttributesOfHouseOwner saveOneHouseOwnerAttribute(
            @RequestBody ModelAttrOfHouseOwnerDto modelAttrOfHouseOwnerDto){

        ModelAttributesOfHouseOwner attributeToReturn=
                modelAttrOfHouseOwnerService.saveOneHouseOwnerAttribute(modelAttrOfHouseOwnerDto);
        //yalnızca houseOwner ekleyince değil, houseOwner attribute güncelleyince de evin attribute leri güncellenmeli
        //buarada houseOwner ın house id sini bulmalıyız
        Long houseId=houseOwnerService.
                getOneOwnerByOwnerId(modelAttrOfHouseOwnerDto.getHouseOwnerId()).getHouse().getHouseId();
//        System.out.println(houseId);
        modelAttrOfHouseService.setAttributesOfOneHouseByOwners(houseId);
        return attributeToReturn;
        //burası bize farklı şeyler dönebilir
    }


    //saveOneModelAttrOfCustomer
    @PostMapping("/saveOrUpdateOneModelAttrOfCustomer")
    public ModelAttributesOfCustomer saveOneCustomerAttribute(
            @RequestBody ModelAttrOfCustomerDto modelAttrOfCustomerDto){
        return modelAttrOfCustomerService.saveOneCustomerAttribute(modelAttrOfCustomerDto);

    }

    //saveOneHouseAttribute
    //house attributeleri bir post ile değil,
    //kendisinde kalan houseowner ların attributeleri ortalaması ile olacak

    //bu  anketlerin update işlemleri daha sonra yazılacak





}

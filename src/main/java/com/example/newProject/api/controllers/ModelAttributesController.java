package com.example.newProject.api.controllers;

import com.example.newProject.DTOs.BasicDtos.CustomerAttributeGetDto;
import com.example.newProject.DTOs.BasicDtos.HouseOwnerAttributeGetDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfCustomerDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfHouseOwnerDto;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfCustomer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Services.BasicServices.HouseOwnerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfCustomerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfHouseOwnerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfHouseService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
            @RequestBody ModelAttrOfHouseOwnerDto modelAttrOfHouseOwnerDto) throws JSONException, IOException, InterruptedException {

        ModelAttributesOfHouseOwner attributeToReturn=
                modelAttrOfHouseOwnerService.saveOneHouseOwnerAttribute(modelAttrOfHouseOwnerDto);
        //yalnızca houseOwner ekleyince değil, houseOwner attribute güncelleyince de evin attribute leri güncellenmeli
        //buarada houseOwner ın house id sini bulmalıyız
        Long houseId=houseOwnerService.
                getOneOwnerByOwnerId(modelAttrOfHouseOwnerDto.getHouseOwnerId()).getHouse().getHouseId();
//        System.out.println(houseId);
        modelAttrOfHouseService.setAttributesOfOneHouseByOwners(houseId);
        return attributeToReturn;
        //customer ı save ederken house larda bi şey değişmeyeğinden
        //save or update costomer burası kadar uzun olmadı
    }


    //saveOneModelAttrOfCustomer
    @PostMapping("/saveOrUpdateOneModelAttrOfCustomer")
    public ModelAttributesOfCustomer saveOneCustomerAttribute(
            @RequestBody ModelAttrOfCustomerDto modelAttrOfCustomerDto) throws JSONException, IOException, InterruptedException {
        return modelAttrOfCustomerService.saveOneCustomerAttribute(modelAttrOfCustomerDto);

    }

    //getOneModelAttributeByCustomerId
    //burada get isteği yapıyoruz ama kotlinde get mapping ile post atamayacağımız için
    //otomatikmen bunu da post yaptık
    @PostMapping("/getOneModelAttributeByCustomerId")
    public ModelAttributesOfCustomer getOneModelAttributeOfCustomer(
            @RequestBody CustomerAttributeGetDto customerAttributeGetDto){
            return modelAttrOfCustomerService.getOneModelAttributeOfCustomer(customerAttributeGetDto);
    }

    //getOneModelAttributeByHouseOwnerId
    //burada get isteği yapıyoruz ama kotlinde get mapping ile post atamayacağımız için
    //otomatikmen bunu da post yaptık
    @PostMapping("/getOneModelAttributeByHouseOwnerId")
    public ModelAttributesOfHouseOwner getOneModelAttributeByHouseOwner(
            @RequestBody HouseOwnerAttributeGetDto houseOwnerAttributeGetDto){
        return modelAttrOfHouseOwnerService.getOneModelAttributeByHouseOwner(houseOwnerAttributeGetDto);
    }





}

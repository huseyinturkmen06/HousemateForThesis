package com.example.newProject.api.controllers;


import com.example.newProject.DTOs.BasicDtos.HouseOwnerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.HouseOwnerUpdateDto;
import com.example.newProject.DTOs.BasicDtos.LoginControlDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Services.BasicServices.HouseOwnerService;
import com.example.newProject.Services.ModelServices.ModelAttrOfHouseService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/houseOwners")
@CrossOrigin
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

    @GetMapping("/getHouseOfHouseOwner/{houseOwnerId}")
    public House getHouseIdOfHouseOwner(@PathVariable Long houseOwnerId){
        return houseOwnerService.getHouseOfHouseOwner(houseOwnerId);
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
    //burası aşırı hata vermeye başladı

    @PostMapping("/saveOneHouseOwner")
    public HouseOwner saveOneHouseOwner(@RequestBody HouseOwnerRegisterDto houseOwnerRegisterDto){
        return houseOwnerService.saveOneHouseOwner(houseOwnerRegisterDto);
        //önce kullanıcıyı kaydet
//        modelAttrOfHouseService.setAttributesOfOneHouseByOwners(houseOwnerRegisterDto.getHouseId());
        //sonra o kullanıcının özelliklerini de göz önüne alarak house özelliğini kaydet
//        return null;
        //üstteki sıra değimesin diye burada bi şey döndürmedim.
        //burasının sonuçlarını db den bakabiliriz
    }

    //updateOneOwner
    @PutMapping("/updateOneHouseOwner")
    public HouseOwner updateOneHouseOwner(@RequestBody HouseOwnerUpdateDto houseOwnerUpdateDto) throws JSONException, IOException, InterruptedException {
        HouseOwner updatedHouseOwner=houseOwnerService.updateOneHouseOwner(houseOwnerUpdateDto);
        //kullanıya burada house da eklemiş oluruz
        //houseId verince bir house a bir houseOwner eklendiği için
        //önce güncelleme ile house houseOwner arasında yeni bir ilişki yaratıyoruz
        modelAttrOfHouseService.setAttributesOfOneHouseByOwners(houseOwnerUpdateDto.getHouseId());
        //sonra houseOwner a yeni verdiğimiz houseId ile setAttributesOfOneHouseByOwners
        //ile güncelleme ile yeni bağlantı yaptığımız house un tüm houseOwnerslarının attributeleri ile
        //gereken evin model attribute lerini belirliyoruz
        return updatedHouseOwner;
    }


    //houseOwner önce ev kayıt olsun
    //sonra ev girsin ve o evin id si dönsün
    // (yani houseOwner ev kaydettiktan hemen sonra updateOneHouseOwner'a bir istek daha atılsın
    //ve houseOwner ın HouseId si de yeni kaydolan evin id değeri olsun


    @PostMapping("/houseOwnerLogin")
    public HouseOwner houseOwnerLoginControl(@RequestBody LoginControlDto loginInfos){
        return houseOwnerService.houseOwnerLoginControl(loginInfos);
    }



    //updatePasswordOfOneOwner
    @PutMapping("/updateHouseOwnerPasswordByUsername")
    public HouseOwner updateHouseOwnerPasswordByUsername(@RequestBody LoginControlDto loginControlDto){
        return houseOwnerService.updateHouseOwnerPasswordByUsername(loginControlDto);
    }


    @GetMapping("/getOneHouseOwnerByMail/{mail}")
    public HouseOwner getOneHouseOwnerByMail(@PathVariable String mail){
        return houseOwnerService.getOneHouseOwnerByMail(mail);
    }




}

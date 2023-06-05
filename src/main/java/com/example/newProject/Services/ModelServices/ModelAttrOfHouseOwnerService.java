package com.example.newProject.Services.ModelServices;

import com.example.newProject.DTOs.BasicDtos.HouseOwnerAttributeGetDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfHouseOwnerDto;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Model_Integration.PredictionHandler;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseOwnerRepo;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Service
public class ModelAttrOfHouseOwnerService {

    ModelAttrOfHouseOwnerRepo modelAttrOfHouseOwnerRepo;
    HouseOwnerRepository houseOwnerRepository;
    @Autowired
    public ModelAttrOfHouseOwnerService(ModelAttrOfHouseOwnerRepo modelAttrOfHouseOwnerRepo,
                                   HouseOwnerRepository houseOwnerRepository) {
        this.modelAttrOfHouseOwnerRepo = modelAttrOfHouseOwnerRepo;
        this.houseOwnerRepository=houseOwnerRepository;
    }

    //getOneModelAttributeByHouseOwnerId
    @GetMapping("/getOneModelAttributeByHouseOwnerId")
    public ModelAttributesOfHouseOwner getOneModelAttributeByHouseOwner(
            @RequestBody HouseOwnerAttributeGetDto houseOwnerAttributeGetDto){
        Long ownerId= houseOwnerAttributeGetDto.getHouseOwnerId();
        HouseOwner owner = houseOwnerRepository.findById(ownerId).orElse(null);
        ModelAttributesOfHouseOwner attributeToReturn=
                modelAttrOfHouseOwnerRepo.findByHouseOwner(owner);
        return attributeToReturn;
    }




    //save or update one ModelAttribute of houseOwner   (poll)
    public ModelAttributesOfHouseOwner saveOneHouseOwnerAttribute(ModelAttrOfHouseOwnerDto modelAttrOfHouseOwnerDto) throws JSONException, IOException, InterruptedException {
        System.out.println(modelAttrOfHouseOwnerDto.getHouseOwnerId());
        System.out.println("*************************");
        ModelAttributesOfHouseOwner attrOfHouseOwnerToSave=
                modelAttrOfHouseOwnerRepo.
                        findByHouseOwner(houseOwnerRepository.findById(modelAttrOfHouseOwnerDto.getHouseOwnerId()).
                                orElse(null));
        //önce houseOwner a göre attribute ü çek,
        //eğer tabloda o house owner a ait attribute yoksa yeni attribute kaydı oluştur.
        if(attrOfHouseOwnerToSave==null){
            attrOfHouseOwnerToSave=new ModelAttributesOfHouseOwner();
        }

        attrOfHouseOwnerToSave.setPrice(modelAttrOfHouseOwnerDto.getPrice());
        attrOfHouseOwnerToSave.setSleepTime(modelAttrOfHouseOwnerDto.getSleepTime());
        attrOfHouseOwnerToSave.setSmooking(modelAttrOfHouseOwnerDto.getSmooking());
        attrOfHouseOwnerToSave.setHavingPet(modelAttrOfHouseOwnerDto.getHavingPet());
        attrOfHouseOwnerToSave.setLuxury(modelAttrOfHouseOwnerDto.getLuxury());
        attrOfHouseOwnerToSave.setGpa(modelAttrOfHouseOwnerDto.getGpa());
        attrOfHouseOwnerToSave.setRentingDuration(modelAttrOfHouseOwnerDto.getRentingDuration());
        //****************
        HouseOwner houseOwnerForForeignKey =
                houseOwnerRepository.findById(modelAttrOfHouseOwnerDto.getHouseOwnerId()).orElse(null);
        if(houseOwnerForForeignKey!=null){
            System.out.println("Service: Modele eklenecek ev bulundu");
            attrOfHouseOwnerToSave.setHouseOwner(houseOwnerForForeignKey);
            //customer nesnesi de setlendi

            //*********************************** model class ın ile haberleşme başlangıcı
//*********************************************************

            String sleepTime=modelAttrOfHouseOwnerDto.getSleepTime();

            System.out.println("sleep time geliyor mu?");
            System.out.println(sleepTime+"\n");

            ///////*********************
            ///////*********************
            ///////*********************

            String smooking="";
            if(modelAttrOfHouseOwnerDto.getSmooking()==true)  smooking="yes";
            else smooking="no";

            String havingAPet="";
            if(modelAttrOfHouseOwnerDto.getHavingPet()==true) havingAPet="yes";
            else havingAPet="no";

            String luxuryCare=Integer.toString(modelAttrOfHouseOwnerDto.getLuxury());

            String gpa=String. valueOf(modelAttrOfHouseOwnerDto.getGpa());

            //age yerine şimdilik 0 veriyorum çünkü age bizim db de yok
            String age="0";



            String rentingDuration=Integer.toString(modelAttrOfHouseOwnerDto.getRentingDuration());

            String price=Integer.toString(modelAttrOfHouseOwnerDto.getPrice());
//*********************************************************************
//*********************************************************************

            //autowired hatasından dolayı methodu static yaptık ve öyle çağırdık
            //function call
            String classOfHouseOwner= PredictionHandler.ModelFunction(
                    sleepTime,smooking,havingAPet,luxuryCare, gpa,age,rentingDuration,price);

            //setting model class attribute to saving object
            attrOfHouseOwnerToSave.setClassOfHouseOwner(classOfHouseOwner);


            return modelAttrOfHouseOwnerRepo.save(attrOfHouseOwnerToSave);
        }
        else{
            System.out.println("Girilen id ye ait ev bulunamadı");
            return null;
        }

    }


    //updateOnePollOfHouseOwner
    //ITS OKAY HERE


}

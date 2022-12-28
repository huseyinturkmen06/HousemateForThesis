package com.example.newProject.Services.ModelServices;

import com.example.newProject.DTOs.BasicDtos.HouseOwnerAttributeGetDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfHouseOwnerDto;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

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




    //save one ModelAttribute of houseOwner   (poll)
    public ModelAttributesOfHouseOwner saveOneHouseOwnerAttribute(ModelAttrOfHouseOwnerDto modelAttrOfHouseOwnerDto){
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
            return modelAttrOfHouseOwnerRepo.save(attrOfHouseOwnerToSave);
        }
        else{
            System.out.println("Girilen id ye ait ev bulunamadı");
            return null;
        }

    }


    //updateOnePollOfHouseOwner


}

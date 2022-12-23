package com.example.newProject.Services.ModelServices;

import com.example.newProject.DTOs.ModelDtos.ModelAttrOfHouseOwnerDto;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseOwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //save one ModelAttribute of houseOwner   (poll)
    public ModelAttributesOfHouseOwner saveOneHouseOwnerAttribute(ModelAttrOfHouseOwnerDto modelAttrOfHouseOwnerDto){
        ModelAttributesOfHouseOwner attrOfHouseOwnerToSave= new ModelAttributesOfHouseOwner();
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

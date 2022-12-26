package com.example.newProject.Services.ModelServices;

import com.example.newProject.DTOs.BasicDtos.HouseOwnerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.HouseSaveDto;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.ModelEntities.LuxuryAttributesOfHouse;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import com.example.newProject.Repositories.BasicRepos.LuxuryAttributeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuxuryAttributeService {

    LuxuryAttributeRepo luxuryAttributeRepo;
    HouseRepository houseRepository;
    @Autowired
    public LuxuryAttributeService(LuxuryAttributeRepo luxuryAttributeRepo,HouseRepository houseRepository) {
        this.luxuryAttributeRepo = luxuryAttributeRepo;
        this.houseRepository=houseRepository;
    }


    //saveOneLuxuryAttributeOfHouse
    public LuxuryAttributesOfHouse saveOneLuxuryAttributeOfHouse(HouseSaveDto houseSaveDto,
                                                                 Long houseId){
        LuxuryAttributesOfHouse luxuryAttributeToSave = new LuxuryAttributesOfHouse();

        luxuryAttributeToSave.setCountOfBathroom(houseSaveDto.getCountOfBathroom());
        luxuryAttributeToSave.setCountOfBedroom(houseSaveDto.getCountOfBedroom());
        luxuryAttributeToSave.setCountOfSalon(houseSaveDto.getCountOfSalon());
        luxuryAttributeToSave.setCountOfOwner(houseSaveDto.getCountOfOwner());
        //burayı manuel değil, farklı şekilde almak gerek
        luxuryAttributeToSave.setHeatResource(houseSaveDto.getHeatResource());
        luxuryAttributeToSave.setFurnished(houseSaveDto.getFurnished());
        luxuryAttributeToSave.setInternetPaved(houseSaveDto.getInternetPaved());
        luxuryAttributeToSave.setFloor(houseSaveDto.getFloor());
        //-------------------------------------
        House houseForForeignKey= houseRepository.findById(houseId).orElse(null);
        //houseId ile house nesnesini bulmuş olduk
        //luxury attribute e foreign key olarak gidecek
        //-------------
        luxuryAttributeToSave.setHouse(houseForForeignKey);
        return luxuryAttributeRepo.save(luxuryAttributeToSave);

    }

}

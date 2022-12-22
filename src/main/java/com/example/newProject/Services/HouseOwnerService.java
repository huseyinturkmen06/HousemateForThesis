package com.example.newProject.Services;

import com.example.newProject.DTOs.CustomerRegisterDto;
import com.example.newProject.DTOs.CustomerUpdateDto;
import com.example.newProject.DTOs.HouseOwnerRegisterDto;
import com.example.newProject.DTOs.HouseOwnerUpdateDto;
import com.example.newProject.Entities.Customer;
import com.example.newProject.Entities.HouseOwner;
import com.example.newProject.Repositories.HouseOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseOwnerService {

    HouseOwnerRepository houseOwnerRepository;
    @Autowired
    public HouseOwnerService(HouseOwnerRepository houseOwnerRepository) {
        this.houseOwnerRepository = houseOwnerRepository;
    }

    //house owner ın evi var, evin de customer ları var
    //houseOwner ile customer arasında direkt bir ilişki yok

    //getAllOwners
    public List<HouseOwner> getAllOwners(){
        return houseOwnerRepository.findAll();
    }


    //normalde username ve id yi path variable olarak almamak gerekir ama şimdilik böyle kalacak
    //getOneOwnerByUserName
    public HouseOwner getOneOwnerByOwnerUserName(String ownerUserName){
        HouseOwner houseOwner=houseOwnerRepository.findHouseOwnerByOwnerUsername(ownerUserName);
        return houseOwner;
    }


    //getOneOwnerById
    public HouseOwner getOneOwnerByOwnerId(Long ownerId){
        HouseOwner houseOwner=houseOwnerRepository.findById(ownerId).orElse(null);
        return houseOwner;
    }

    //saveOneOwner
    public HouseOwner saveOneHouseOwner(HouseOwnerRegisterDto houseOwnerRegisterDto){
        HouseOwner houseOwnerToSave = new HouseOwner();
        houseOwnerToSave.setOwnerName(houseOwnerRegisterDto.getHouseOwnerName());
        houseOwnerToSave.setOwnerSurname(houseOwnerRegisterDto.getHouseOwnerSurname());
        houseOwnerToSave.setOwnerUsername(houseOwnerRegisterDto.getHouseOwnerUsername());
        houseOwnerToSave.setOwnerMail(houseOwnerRegisterDto.getHouseOwnerEmail());
        houseOwnerToSave.setOwnerPassword(houseOwnerRegisterDto.getHouseOwnerPassword());
        houseOwnerToSave.setOwnerAge(0);
        houseOwnerToSave.setOwnerHometown("");
        houseOwnerToSave.setOwnerDepartment("");
        houseOwnerToSave.setOwnerGrade(0);
        houseOwnerToSave.setOwnerPhone("");
        houseOwnerToSave.setOwnerGender("");
        //list of housecustomer henüz olmadı
        return houseOwnerRepository.save(houseOwnerToSave);

    }

    //updateOneOwner
    //şimdi burası yazılaak
    public HouseOwner updateOneHouseOwner(HouseOwnerUpdateDto houseOwnerUpdateDto){
        HouseOwner houseOwnerToUpdate= houseOwnerRepository.findById(houseOwnerUpdateDto.getHouseOwnerId()).orElse(null);
        if(houseOwnerToUpdate!=null){
            //id yi yeniden değiştirmedik çünkü yalnızca gereken customer ı bulmak için kullandık
            houseOwnerToUpdate.setOwnerName(houseOwnerUpdateDto.getHouseOwnerName());
            houseOwnerToUpdate.setOwnerSurname(houseOwnerUpdateDto.getHouseOwnerSurname());
            houseOwnerToUpdate.setOwnerUsername(houseOwnerUpdateDto.getHouseOwnerUsername());
            houseOwnerToUpdate.setOwnerAge(houseOwnerUpdateDto.getHouseOwnerAge());
            houseOwnerToUpdate.setOwnerHometown(houseOwnerUpdateDto.getHouseOwnerHometown());
            houseOwnerToUpdate.setOwnerDepartment(houseOwnerUpdateDto.getHouseOwnerDepartment());
            houseOwnerToUpdate.setOwnerGrade(houseOwnerUpdateDto.getHouseOwnerGrade());
            houseOwnerToUpdate.setOwnerPhone(houseOwnerUpdateDto.getHouseOwnerPhone());
            houseOwnerToUpdate.setOwnerMail(houseOwnerUpdateDto.getHouseOwnerEmail());
            houseOwnerToUpdate.setOwnerGender(houseOwnerUpdateDto.getHouseOwnerGender());
            return houseOwnerRepository.save(houseOwnerToUpdate);

        }
        else {
            return null;
        }
    }

    //updatePasswordOfOneOwner



}

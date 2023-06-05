package com.example.newProject.Services.BasicServices;

import com.example.newProject.DTOs.BasicDtos.HouseOwnerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.HouseOwnerUpdateDto;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseOwnerService {

    HouseOwnerRepository houseOwnerRepository;
    HouseRepository houseRepository;
    @Autowired
    public HouseOwnerService(HouseOwnerRepository houseOwnerRepository,
                             HouseRepository houseRepository) {
        this.houseOwnerRepository = houseOwnerRepository;
        this.houseRepository=houseRepository;
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
        //buraya bir de houseId gelmeli ve (path varieble)
        //gelen houseId ile gerekli ev nesnesi bulunup houseOwner.setHouse(houseId) ile setleme yapılmalı

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

        //----------- eski kod-------
//            House houseForForeignKey = houseRepository.findById(houseOwnerUpdateDto.getHouseId()).orElse(null);
//            houseOwnerToUpdate.setHouse(houseForForeignKey);

        //----------- yeni kod-------
        House houseForForeignKey = houseRepository.findById(1L).orElse(null);
        //save ederken 1 id li house u aldık (ik kayıtta)
        houseOwnerToSave.setHouse(houseForForeignKey);
        //foreign key her zaman 1 id li house oldu
        //şimdi houseOwner eklerken houseId almamıza gerek yok artık



        //şimdilik yeni kaydolan her houseOwner 1 numaralı house'a kaydoluyor
        //zaten bir houseOwner ın 1 tane house bilgisi olabilir
        //kullanıcı gerçekten ev girmek istediğinde ise yeni bir ev oluşturulur ve
        //kullanıcının yalnızca house nesnesi (foreign key) değişmiş olur








        return houseOwnerRepository.save(houseOwnerToSave);

    }

    //updateOneOwner
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

            //buraya geldiğimizde
            //controllerdan update i çağırdığımızda , house un db de olması bekleniyor


            House houseForForeignKey = houseRepository.findById(houseOwnerUpdateDto.getHouseId()).orElse(null);
            houseOwnerToUpdate.setHouse(houseForForeignKey);
            //null kontolü vs yapılacak her yere



            return houseOwnerRepository.save(houseOwnerToUpdate);

        }
        else {
            return null;
        }
    }

    //updatePasswordOfOneOwner



}

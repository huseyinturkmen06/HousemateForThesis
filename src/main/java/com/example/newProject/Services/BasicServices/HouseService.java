package com.example.newProject.Services.BasicServices;


import com.example.newProject.DTOs.BasicDtos.HouseSaveDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.BasicRepos.HavingRelationRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {
    HouseRepository houseRepository;
    HavingRelationRepository havingRelationRepository;
    CustomerRepository customerRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository,HavingRelationRepository havingRelationRepository,CustomerRepository customerRepository) {
        this.houseRepository = houseRepository;
        this.havingRelationRepository = havingRelationRepository;
        this.customerRepository = customerRepository;
    }

    public List<HouseCustomer> getRalationsOfOneCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        return havingRelationRepository.findHavingRelationsByCustomer(customer);
    }


    //special method
    //Get all houses of one customer
    public List<House> getAllHousesOfOneCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        List<HouseCustomer> relaions=havingRelationRepository.findHavingRelationsByCustomer(customer);
        //fe found all relations of one customer
        List<House> allHousesOfOneUser=new ArrayList<>();
        for(HouseCustomer relation: relaions){
            allHousesOfOneUser.add(relation.getHouse());
        }
        //we found all houses of one customer

        return allHousesOfOneUser;
    }


    //Get all houses
    public List<House> getAllHouses(){
        return houseRepository.findAll();
    }


    //house id yi şimdilik path variable ilealıyoruz ama ilerde sto ile alıncak
    public House getOneHouseById(Long houseId){
        return houseRepository.findById(houseId).orElse(null);
    }



    //getHousesByAddress ----->House lara adreslerini belli eden attribute ler ler eklenmeli

    //saveOneHouse
    public House saveOneHouse(HouseSaveDto houseSaveDto){
        House houseToSave= new House();
        //id yi almadık çünkü otomatik gelecek
        houseToSave.setHouseAddress(houseSaveDto.getHouseAddress());
//        houseToSave.setCountOfBathroom(houseSaveDto.getCountOfBathroom());
//        houseToSave.setCountOfBedroom(houseSaveDto.getCountOfBedroom());
//        houseToSave.setCountOfSalon(houseSaveDto.getCountOfSalon());
//        houseToSave.setCountOfOwner(houseSaveDto.getCountOfOwner());
        houseToSave.setHouseType("");

        //şimdilik houseType ı boş geçiyoruz ama
        //normalde housetype için house un Luxury attributes tablosundan verilerni çekip bir karara varıyoruz
        //bunu sınvlar bittikten hemen sonra tamamlabiliriz
        //ve şimdilik sadece

        houseToSave.setHeatResource(houseSaveDto.getHeatResource());
        houseToSave.setFurnished(houseSaveDto.getFurnished());
        houseToSave.setInternetPaved(houseSaveDto.getInternetPaved());
        houseToSave.setFloor(houseSaveDto.getFloor());
        houseToSave.setRent(houseSaveDto.getRent());
        return houseRepository.save(houseToSave);
    }

    //updateOneHouse
    public House updateOneHouse(HouseSaveDto houseSaveDto){
        House houseToSave= new House();
        return null;
    }














}

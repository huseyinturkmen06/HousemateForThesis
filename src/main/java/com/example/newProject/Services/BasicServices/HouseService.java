package com.example.newProject.Services.BasicServices;


import com.example.newProject.DTOs.BasicDtos.HouseSaveDto;
import com.example.newProject.DTOs.BasicDtos.HouseUpdateDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.BasicRepos.HavingRelationRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseRepo;
import com.example.newProject.Services.ModelServices.LuxuryAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {
    private HouseRepository houseRepository;
    private HavingRelationRepository havingRelationRepository;
    private CustomerRepository customerRepository;
    private LuxuryAttributeService luxuryAttributeService;
    private ModelAttrOfHouseRepo modelAttrOfHouseRepo;

    @Autowired
    public HouseService(HouseRepository houseRepository,
                        HavingRelationRepository havingRelationRepository,
                        CustomerRepository customerRepository,
                        LuxuryAttributeService luxuryAttributeService,
                        ModelAttrOfHouseRepo modelAttrOfHouseRepo) {
        this.houseRepository = houseRepository;
        this.havingRelationRepository = havingRelationRepository;
        this.customerRepository = customerRepository;
        this.luxuryAttributeService=luxuryAttributeService;
        this.modelAttrOfHouseRepo=modelAttrOfHouseRepo;
    }

    public List<HouseCustomer> getRalationsOfOneCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        return havingRelationRepository.findHavingRelationsByCustomer(customer);
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

        int roomCount= houseSaveDto.getCountOfBedroom();
        int saloonCount= houseSaveDto.getCountOfSalon();
        String typeAsString= Integer.toString(roomCount)+"+"+Integer.toString(saloonCount);

        houseToSave.setHouseType(typeAsString);

        houseToSave.setHeatResource(houseSaveDto.getHeatResource());
        houseToSave.setFurnished(houseSaveDto.getFurnished());
        houseToSave.setInternetPaved(houseSaveDto.getInternetPaved());
        houseToSave.setFloor(houseSaveDto.getFloor());
        houseToSave.setRent(houseSaveDto.getRent());
        House houseToReturn = houseRepository.save(houseToSave);

        //house kaydederken luxury attribute ünü de kaydet
        luxuryAttributeService.saveOneLuxuryAttributeOfHouse(houseSaveDto,houseToReturn.getHouseId());

        return houseToReturn;
    }
    //house temel bir entity olduğundan ve kimseden foreign key  almadığından
    //save or update house  diye bir metod yazmıyoruz
    //sace ve update i ayrı yazıyoruz




    //updateOneHouse
    //houseUpdate DTO alacak, tüm özelliklerin yanında bir de houseId
    //gelen attributeler ile hem luxury hem de house un normal özellikleri de güncellenecek

    public House updateOneHouse(HouseSaveDto houseSaveDto){
        House houseToSave= new House();


        return null;
    }


    //1 class isminin tüm kayıtlarını getiren metod
    public List<House> getHousesByClass(String classOfHouse){
        ArrayList<ModelAttributesOfHouse> allAttributeRecordsOfClass= modelAttrOfHouseRepo.findAllByClassOfHouse(classOfHouse);
        //şimdi bir class ın tüm attribute kayıtlarını aldık
        //bunların da her birisinin evini almamız gerek
        ArrayList<House> housesToReturn= new ArrayList<>();

        for(ModelAttributesOfHouse record: allAttributeRecordsOfClass){
            housesToReturn.add(record.getHouse());
        }
        return housesToReturn;
    }


    public House updateOneHouseByHouseId(Long houseId, HouseUpdateDto houseResponse) {
        House houseToUpdate = houseRepository.findById(houseId).orElse(null);
        houseToUpdate.setFloor(houseResponse.getFloor());
        houseToUpdate.setFurnished(houseResponse.getFurnished());
        houseToUpdate.setHeatResource(houseResponse.getHeatResource());
        houseToUpdate.setHouseAddress(houseResponse.getHouseAddress());
        houseToUpdate.setHouseType(houseResponse.getHouseType());
        houseToUpdate.setInternetPaved(houseResponse.getInternetPaved());
        houseToUpdate.setRent(houseResponse.getRent());
        houseRepository.save(houseToUpdate);
        return houseToUpdate;

    }
}

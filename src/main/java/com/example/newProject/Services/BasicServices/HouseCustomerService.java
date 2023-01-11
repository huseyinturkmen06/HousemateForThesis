package com.example.newProject.Services.BasicServices;

import com.example.newProject.DTOs.BasicDtos.CreateRelationDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.BasicRepos.HavingRelationRepository;
import com.example.newProject.Repositories.BasicRepos.HouseCustomerRepo;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseCustomerService {


    private HouseRepository houseRepository;
    private CustomerRepository customerRepository;
    private HouseCustomer houseCustomer;
    private HouseCustomerRepo houseCustomerRepo;
    private HavingRelationRepository havingRelationRepository;


    @Autowired
    public HouseCustomerService(HouseRepository houseRepository, CustomerRepository customerRepository,
                                HouseCustomerRepo houseCustomerRepo,HavingRelationRepository havingRelationRepository) {
        this.houseRepository = houseRepository;
        this.customerRepository = customerRepository;
        this.houseCustomerRepo = houseCustomerRepo;
        this.havingRelationRepository=havingRelationRepository;

    }






    public HouseCustomer createOneRelation(CreateRelationDto createRelationDto){
        House houseToSave = houseRepository.findById(createRelationDto.getHouseId()).orElse(null);
        Customer customerToSave = customerRepository.findById(createRelationDto.getCustomerId()).orElse(null);
        HouseCustomer relationToSave = new HouseCustomer();
        relationToSave.setCustomer(customerToSave);
        relationToSave.setHouse(houseToSave);
        return houseCustomerRepo.save(relationToSave);

    }


    //special method
    //Get all houses of one customer
    public List<House> getAllHousesOfOneCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        List<HouseCustomer> relaions=havingRelationRepository.findHavingRelationsByCustomer(customer);
        //bir customer ın tüm ilişkileri bulduk
        //fe found all relations of one customer
        List<House> allHousesOfOneUser=new ArrayList<>();
        for(HouseCustomer relation: relaions){
            allHousesOfOneUser.add(relation.getHouse());
        }
        //we found all houses of one customer

        return allHousesOfOneUser;
    }


    //1 tane house çek ve onun içinde olduğu tüm ilişkilerini bul
    //sonra bu ilişkilerin her birisinin customerını bul ve listeye ekle
    //sonuçta bir evin tüm customerlarını bulmuş oluruz
    public List<Customer> getAllCustomersOfOneHouse(Long id){
        House house = houseRepository.findById(id).orElse(null);
        List<HouseCustomer> relaions=havingRelationRepository.findHavingRelationsByHouse(house);
        //fe found all relations of one house
        List<Customer> allCustomersOfOneHouse=new ArrayList<>();
        for(HouseCustomer relation: relaions){
            allCustomersOfOneHouse.add(relation.getCustomer());
        }
        //we found all houses of one customer

        return allCustomersOfOneHouse;
    }


}

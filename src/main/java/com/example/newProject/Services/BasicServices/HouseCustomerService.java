package com.example.newProject.Services.BasicServices;

import com.example.newProject.DTOs.BasicDtos.CreateRelationDto;
import com.example.newProject.DTOs.BasicDtos.DeleteRelationDto;
import com.example.newProject.DTOs.BasicDtos.LikeControlDto;
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
        //bir customer ??n t??m ili??kileri bulduk
        //fe found all relations of one customer
        List<House> allHousesOfOneUser=new ArrayList<>();
        for(HouseCustomer relation: relaions){
            allHousesOfOneUser.add(relation.getHouse());
        }
        //we found all houses of one customer

        return allHousesOfOneUser;
    }


    //1 tane house ??ek ve onun i??inde oldu??u t??m ili??kilerini bul
    //sonra bu ili??kilerin her birisinin customer??n?? bul ve listeye ekle
    //sonu??ta bir evin t??m customerlar??n?? bulmu?? oluruz
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


    //kullan??c?? ile ev aras??nda bir be??enme ili??kisi var m?? diye kontrol eden method
    public Boolean likeControl(LikeControlDto likeControlDto){
        List<Customer> customersOfHouse= getAllCustomersOfOneHouse(likeControlDto.getHouseId());
        Boolean keyToReturn=false;
        Customer customerToControl = customerRepository.findById(likeControlDto.getCustomerId()).orElse(null);
        for(Customer customer:customersOfHouse){
            if(customerToControl==customer){
                keyToReturn = true;
            };
            //DTo ile al??nan customerToControl house'u be??enen customer lar??n herhangi birisiyle e??le??irse
            //key i true yap??yoruz, yoksa false kal??r
        }
        return keyToReturn;
    }

    public HouseCustomer deleteOneRelation(DeleteRelationDto deleteRelationDto){
        House house= houseRepository.findById(deleteRelationDto.getHouseId()).orElse(null);
        Customer customer = customerRepository.findById(deleteRelationDto.getCustomerId()).orElse(null);
        HouseCustomer relationToDelete = houseCustomerRepo.findByHouseAndCustomer(house,customer);
        houseCustomerRepo.delete(relationToDelete);
        return relationToDelete;
    }











}

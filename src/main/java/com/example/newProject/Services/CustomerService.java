package com.example.newProject.Services;

//generation type ın db ye göre artması gerekiyo, gerekirse bi metod yazılsın

import com.example.newProject.DTOs.CustomerRegisterDto;
import com.example.newProject.DTOs.CustomerUpdateDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Repositories.CustomerRepository;
import com.example.newProject.Repositories.HavingRelationRepository;
import com.example.newProject.Repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    HouseRepository houseRepository;
    HavingRelationRepository havingRelationRepository;
    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(HouseRepository houseRepository,HavingRelationRepository havingRelationRepository,CustomerRepository customerRepository) {
        this.houseRepository = houseRepository;
        this.havingRelationRepository = havingRelationRepository;
        this.customerRepository = customerRepository;
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

    //get all customers in db
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }



    //getOneCustomerById
    public Customer getOneCustomerById(Long customerId){
        return customerRepository.findById(customerId).orElse(null);
    }

    //getOneCustomerByCustomerName
    public Customer getOneCustomerByUsername(String customerUsername){
        return customerRepository.findCustomerByCustomerUsername(customerUsername);
    }


    //neralerin başta null, nerelerin boş string olacağına bakmamız gerek
    //save one customer
    //bu customer daha önce var mı diye kontrol edilecek

    public Customer saveOneCustomer(CustomerRegisterDto customerRegisterDto){
        Customer customerToSave = new Customer();
        customerToSave.setCustomerName(customerRegisterDto.getCustomerName());
        customerToSave.setCustomerSurname(customerRegisterDto.getCustomerSurname());
        customerToSave.setCustomerUsername(customerRegisterDto.getCustomerUsername());
        customerToSave.setCustomerEmail(customerRegisterDto.getCustomerEmail());
        customerToSave.setCustomerPassword(customerRegisterDto.getCustomerPassword());
        customerToSave.setCustomerAge(0);
        customerToSave.setCustomerHometown("");
        customerToSave.setCustomerDepartment("");
        customerToSave.setCustomerGrade(0);
        customerToSave.setCustomerPhone("");
        customerToSave.setCustomerGender("");
        //list of housecustomer henüz olmadı
        return customerRepository.save(customerToSave);

    }

    //update one customer
    //user ın main atributelerinin id hariç hepsini güncelliyoruz
    //customer a direkt olarak bir house eklenmeyecek,
    //yalnızca bir customer bir house a beğeniye tıklayınca
    //HouseCustomer tablosunda customerId ve houseId ile ilişki oluşacak

    //customerIn parola değişim ekranı farklı olacak
    //farklı bir dto ile update yapılcak ve normal update de password alınmayacak


    public Customer updateOneCustomer(CustomerUpdateDto customerUpdateDto){
        Customer customerToUpdate= customerRepository.findById(customerUpdateDto.getCustomerId()).orElse(null);
        if(customerToUpdate!=null){
            //id yi yeniden değiştirmedik çünkü yalnızca gereken customer ı bulmak için kullandık
            customerToUpdate.setCustomerName(customerUpdateDto.getCustomerName());
            customerToUpdate.setCustomerSurname(customerUpdateDto.getCustomerSurname());
            customerToUpdate.setCustomerUsername(customerUpdateDto.getCustomerUsername());
            customerToUpdate.setCustomerAge(customerUpdateDto.getCustomerAge());
            customerToUpdate.setCustomerHometown(customerUpdateDto.getCustomerHometown());
            customerToUpdate.setCustomerDepartment(customerUpdateDto.getCustomerDepartment());
            customerToUpdate.setCustomerGrade(customerUpdateDto.getCustomerGrade());
            customerToUpdate.setCustomerPhone(customerUpdateDto.getCustomerPhone());
            customerToUpdate.setCustomerEmail(customerUpdateDto.getCustomerEmail());
            customerToUpdate.setCustomerGender(customerUpdateDto.getCustomerGender());
            return customerRepository.save(customerToUpdate);


        }
        else {
            return null;
        }
    }





}

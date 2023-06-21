package com.example.newProject.Services.BasicServices;

//generation type ın db ye göre artması gerekiyo, gerekirse bi metod yazılsın

import com.example.newProject.DTOs.BasicDtos.CustomerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.CustomerUpdateDto;
import com.example.newProject.DTOs.BasicDtos.LoginControlDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseCustomer;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.BasicRepos.HavingRelationRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import com.example.newProject.Services.password.PasswordLoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    HouseRepository houseRepository;
    HavingRelationRepository havingRelationRepository;
    CustomerRepository customerRepository;

    PasswordLoginUtil passwordLoginUtil;

    @Autowired
    public CustomerService(HouseRepository houseRepository,HavingRelationRepository havingRelationRepository,
                           CustomerRepository customerRepository,PasswordLoginUtil passwordLoginUtil) {
        this.houseRepository = houseRepository;
        this.havingRelationRepository = havingRelationRepository;
        this.customerRepository = customerRepository;
        this.passwordLoginUtil= passwordLoginUtil;
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


    //encode password and save
    public Customer saveOneCustomer(CustomerRegisterDto customerRegisterDto){
        Customer customerToSave = new Customer();
        customerToSave.setCustomerName(customerRegisterDto.getCustomerName());
        customerToSave.setCustomerSurname(customerRegisterDto.getCustomerSurname());
        customerToSave.setCustomerUsername(customerRegisterDto.getCustomerUsername());
        customerToSave.setCustomerEmail(customerRegisterDto.getCustomerEmail());
        //save and encode password
        customerToSave.setCustomerPassword(
                passwordLoginUtil.encodePassword(customerRegisterDto.getCustomerPassword())
                );
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



    //login control
    public Customer customerLoginControl(LoginControlDto loginInfos) {
        System.out.println(loginInfos.getUsername()+loginInfos.getPassword());
        return passwordLoginUtil.customerLoginControl(loginInfos.getUsername(),loginInfos.getPassword());
    }


    public Customer updateCustomerPasswordByUsername(LoginControlDto loginControlDto) {
        Customer customer = customerRepository.findCustomerByCustomerUsername(loginControlDto.getUsername());
        customer.setCustomerPassword(   passwordLoginUtil.encodePassword(  loginControlDto.getPassword() )     );
        customerRepository.save(customer);
        return customer;




    }

    public Customer getOneCustomerByMail(String mail) {
        Customer customer = customerRepository.findCustomerByCustomerEmail(mail);
        return customer;
    }
}

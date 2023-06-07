package com.example.newProject.api.controllers;

import com.example.newProject.DTOs.BasicDtos.CustomerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.CustomerUpdateDto;
import com.example.newProject.DTOs.BasicDtos.LoginControlDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Services.BasicServices.CustomerService;
import com.example.newProject.Services.BasicServices.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {


    private HouseService houseService;
    private CustomerService customerService;


    @Autowired
    public CustomerController(HouseService houseService,CustomerService customerService) {
        this.houseService = houseService;
        this.customerService=customerService;
    }


    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    //getOneCustomerById
    @GetMapping("/getOneCustomerById/{customerId}")
    public Customer getOneCustomerById(@PathVariable Long customerId) {
        return customerService.getOneCustomerById(customerId);
    }

    //getOneCustomerByCustomerName
    @GetMapping("/getOneCustomerByUsername/{customerUsername}")
    public Customer getOneCustomerByUsername(@PathVariable String customerUsername){
        return customerService.getOneCustomerByUsername(customerUsername);
    }

    @PostMapping("/saveOneCustomer")
    public Customer saveOneCustomer(@RequestBody CustomerRegisterDto customerRegisterDto){
        return customerService.saveOneCustomer(customerRegisterDto);
    }

    @PutMapping("/updateOneCustomer")
    public Customer updateOneCustomer(@RequestBody CustomerUpdateDto customerUpdateDto){
        return customerService.updateOneCustomer(customerUpdateDto);
    }
    //sanırım update işleminde put yapmamızın sebebi save metodunu var olan bir id ile kullanmya izin vermesi ama
    //nasıl olduysa post ile deneyince de izin verdi buna, araştırmaya devam edicem


    //updatePasswordOfOneUser

    //kullanıcı house a tıklamış mı diye kontrol edecek endpoint
    // house a id nin kaydı ile relation çekilcek
    //o relationun customer ı elimizdeki customer ile aynı ise true dönecek
    //kullanıcı bir evi beğenince birden fazla beğenirse tabloda fazladan kayıt oluşuyor


    @GetMapping("/customerLogin")
    public Boolean customerLoginControl(@RequestBody LoginControlDto loginInfos){
        return customerService.customerLoginControl(loginInfos);
    }




}

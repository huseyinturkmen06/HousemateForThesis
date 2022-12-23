package com.example.newProject.api.controllers;

import com.example.newProject.DTOs.BasicDtos.CustomerRegisterDto;
import com.example.newProject.DTOs.BasicDtos.CustomerUpdateDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Services.BasicServices.CustomerService;
import com.example.newProject.Services.BasicServices.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private HouseService houseService;
    private CustomerService customerService;


    @Autowired
    public CustomerController(HouseService houseService,CustomerService customerService) {
        this.houseService = houseService;
        this.customerService=customerService;
    }


    //özel metod
    @GetMapping("/getAllCustomersOfOneHouse/{houseId}")
    public List<Customer> getAllCustomersOfOneHouse(@PathVariable Long houseId){
        return customerService.getAllCustomersOfOneHouse(houseId);
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



}

package com.example.newProject.Services.password;

import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service

//do not forget service anotation to avoid from usage issues
public class PasswordLoginUtil {




    private BCryptPasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;
    private HouseOwnerRepository houseOwnerRepository;

    @Autowired
    public PasswordLoginUtil(CustomerRepository customerRepository, HouseOwnerRepository houseOwnerRepository,
                             BCryptPasswordEncoder passwordEncoder) {
        this.customerRepository=customerRepository;
        this.houseOwnerRepository=houseOwnerRepository;
        this.passwordEncoder=passwordEncoder;
    }





    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }



    //find customer by username and compare two passwords from db and frontend
    public Boolean customerLoginControl(String customerUsername,String rawPassword) {
        //we are using new instead of dependency injection here,
        //because matches function doesn't work properly the other way
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Customer customer = customerRepository.
                findCustomerByCustomerUsername(customerUsername);
        String hashedPassword = customer.getCustomerPassword();
        boolean matches = passwordEncoder.matches(rawPassword, hashedPassword);
        if (  !  customerRepository.existsById(customer.getCustomerId()) ){
            System.out.println("No such a user!");
            return false;
        } else if (customerRepository.existsById(customer.getCustomerId()) && matches==false) {
            System.out.println("User founded, but false password");
            return false;
        }
        else {
            System.out.println("Welcome!");
            return true;
        }
    }



    //find houseOwner by username and compare two passwords from db and frontend
    public Boolean houseOwnerLoginControl(String houseOwnerUserName,String rawPassword) {
        //we are using new instead of dependency injection here,
        //because matches function doesn't work properly the other way
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        HouseOwner houseOwner = houseOwnerRepository.
                findHouseOwnerByOwnerUsername(houseOwnerUserName);
        String hashedPassword = houseOwner.getOwnerPassword();
        boolean matches = passwordEncoder.matches(rawPassword, hashedPassword);
        if (  !  houseOwnerRepository.existsById(houseOwner.getOwnerId()) ){
            System.out.println("No such a user!");
            return false;
        } else if (houseOwnerRepository.existsById(houseOwner.getOwnerId()) && matches==false) {
            System.out.println("User founded, but false password");
            return false;
        }
        else {
            System.out.println("Welcome!");
            return true;
        }
    }
























}

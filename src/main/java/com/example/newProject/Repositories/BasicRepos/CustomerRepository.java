package com.example.newProject.Repositories.BasicRepos;
import com.example.newProject.Entities.BasicEntities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findCustomerByCustomerUsername(String username);

//    Optional<Customer> findCustomerBy

    Customer findCustomerByCustomerEmail(String mail);


}
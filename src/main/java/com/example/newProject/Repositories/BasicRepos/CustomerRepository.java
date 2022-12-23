package com.example.newProject.Repositories.BasicRepos;
import com.example.newProject.Entities.BasicEntities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findCustomerByCustomerUsername(String username);


}
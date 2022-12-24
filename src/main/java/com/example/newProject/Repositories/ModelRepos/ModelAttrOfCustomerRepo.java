package com.example.newProject.Repositories.ModelRepos;

import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelAttrOfCustomerRepo extends JpaRepository<ModelAttributesOfCustomer,Long> {
    //bunların id değerlerinde bir sıkıntı çıkabilir ama bakarız

    ModelAttributesOfCustomer findByCustomer(Customer customer);


}

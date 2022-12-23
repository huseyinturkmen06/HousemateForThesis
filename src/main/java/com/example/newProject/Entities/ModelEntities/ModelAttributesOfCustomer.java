package com.example.newProject.Entities.ModelEntities;


import com.example.newProject.Entities.BasicEntities.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

//one to one da karşılıklı olrak attributeler istendiği için sıkıntılar çıkıyor, düzelmesi gerek

@Entity
@Table(name="model_of_customer")
@Data

public class ModelAttributesOfCustomer extends ModelAttributes{

    public ModelAttributesOfCustomer() {
    }

    public ModelAttributesOfCustomer(Long id, Customer customer) {
        super(id);
        this.customer=customer;
    }


    //1 tane model attribue nesnesi,1 tane customer a ait olabilir



    //belki videodaki gibi bu tarafı yok edip sadece customerda bir ilişki kurabiliriz
    @JsonIgnore
    @JoinColumn(name="customer_id")
    @OneToOne
    private Customer customer;

//    @Column(name="deneme")
//    private String deneme;

    @Column(name="class_of_customer")
    private String classOfCustomer;


}

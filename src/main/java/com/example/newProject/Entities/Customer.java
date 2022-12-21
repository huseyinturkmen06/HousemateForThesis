package com.example.newProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
//problemi çözmek için şu uygulanabilir
//customer tablosunda customer_attribute_id olmayacak
//sadece customer_attribute tablosuna customer_id foreign key olarak gidecek
//customer ın customer_attribute bilgisini almak istediğimizde de customer_attribute tablosundan
//customer_id ile sorgulama yapıp, gereken customer ın attribute bilgisini alacağız




//one to one --- model attribute,
//one to many (many to many için ara tabloda)
//customer ve houseOwner ın ortak metodları bir parent class larla irleştirilebilirdi ama şimdililk böyle kalacak


@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Customer {

    @Id
//    @GeneratedValue
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_surname")
    private String customerSurname;

    @Column(name="customer_age")
    private Integer customerAge;

    @Column(name="customer_hometown")
    private String customerHometown;

    @Column(name="customer_department")
    private String customerDepartment;

    @Column(name="customer_grade")
    private Integer customerGrade;

    @Column(name="customer_phone")
    private String customerPhone;

    @Column(name="customer_email")
    private String customerEmail;

    @Column(name="customer_gender")
    private String customerGender;

    @Column(name="class_of_customer")
    private String classOfCustomer;


    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    List<HouseCustomer> houseCustomers;


//    @JsonIgnore
////    @JoinColumn(name="cus_model_attr")
//    @OneToOne
//    private ModelAttributesOfCustomer customerModelAttribute;



}

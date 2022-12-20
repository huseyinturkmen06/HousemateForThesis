package com.example.newProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Customer {

    @Id
//    @GeneratedValue
    @Column(name="customer_id")
    private int customerId;

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


    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    List<HouseCustomer> houseCustomers;

}

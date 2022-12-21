package com.example.newProject.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="house_customer")
public class HouseCustomer {

    @Id
    @GeneratedValue
    @Column(name="relation_id")
    private Long relation_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}

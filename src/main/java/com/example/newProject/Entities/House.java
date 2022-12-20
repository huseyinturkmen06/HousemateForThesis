package com.example.newProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="houses")
@Data
@AllArgsConstructor
@NoArgsConstructor

//new comment


public class House {
    @Id
    @GeneratedValue
    @Column(name="house_id")
    private int houseId;

    @Column(name="house_address")
    private String houseAddress;

    @Column(name="count_of_bathroom")
    private int countOfBathroom;

    @Column(name="count_of_bedroom")
    private int countOfBedroom;

    @Column(name="count_of_salon")
    private int countOfSalon;

    @Column(name="count_of_owner")
    private int countOfOwner;

    @Column(name="heat_resource")
    private String heatResource;

    @Column(name="furnished")
    private String furnished;

    @Column(name="internet_paved")
    private String internetPaved;

    @Column(name="floor")
    private int floor;

    @Column(name="rent")
    private int rent;

    @JsonIgnore
    @OneToMany(mappedBy = "house")
    private List<HouseOwner> owners;


    @JsonIgnore
    @OneToMany(mappedBy = "house")
    List<HouseCustomer> houseCustomers;




}

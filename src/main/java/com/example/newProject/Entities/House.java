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

//kullanıcılar luxury attibutelerinı  kendileri girecekler
// (belirli bir text açıklaması ile kendilerine ne tercih etmeleri gerektiği de söylenecek)
//generated value olayı biraz sıkıntılı, onun çeşitlerini inceleyip postman ile de girilenlerin
//çakışmayacağı bir yol bulmak gerekir


public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //burayı şimdilik auto aptım ama 4 çeşidi var

    @Column(name="house_id")
    private Long houseId;

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

    @Column(name="class_of_house")
    private String classOfHouse;




    //****************************************************

    @JsonIgnore
    @OneToMany(mappedBy = "house")
    private List<HouseOwner> owners;

    @JsonIgnore
    @OneToMany(mappedBy = "house")
    List<HouseCustomer> houseCustomers;

//    @JsonIgnore
//    @JoinColumn(name="house_model_attr")
//    @OneToOne
//    ModelAttributesOfHouse houseModelAttribute;




}

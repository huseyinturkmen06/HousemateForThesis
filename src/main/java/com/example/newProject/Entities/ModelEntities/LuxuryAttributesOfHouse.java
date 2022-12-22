package com.example.newProject.Entities.ModelEntities;

import com.example.newProject.Entities.BasicEntities.House;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="luxury_attr_of_house")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuxuryAttributesOfHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long luxuryId;


    //bu class için yeni bir model  oluşturulmalı ve evin lüxlük durumu
    //ayrı verilerle belirlenmeli
    // o zamana kadar evlerin lüxury durumları manuel olarak veya restgele atancak


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



    //her luxury attribute ün yalnız 1 tane house u olacak
    //ver her house un yalnızca 1 tane luxury attribute ü olacak
    //bunun tersini house a yazmadım şimdilik
    @JsonIgnore
    @JoinColumn(name="house_id")
    @OneToOne
    House house;



}

package com.example.newProject.DTOs.ModelDtos;

import lombok.Data;

@Data
public class ModelAttrOfHouseOwnerDto {

    //burada mutlaka anketi doldurulacak houseOwner ın  houseOwner si de alınmalı ki
    //o id ile ModelAttributesOfHouseOwner ın HouseOwner nesnesini de setleyebilelim

    private String sleepTime;

    private Boolean smooking;

    private Boolean havingPet;

    private int luxury;

    private Double gpa;

    private Integer rentingDuration;

    //***************************
    private Long houseOwnerId;

    //burası foreign key houseOwner nesnesini setlemede kullanılacak
}

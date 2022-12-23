package com.example.newProject.DTOs.ModelDtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ModelAttrOfCustomerDto {
    //burada mutlaka anketi doldurulacak customerın customerId si de alınmalı ki
    //o id ile ModelAttributesOfCustomer ın cutomer nesnesini de setleyebilelim

    private String sleepTime;

    private Boolean smooking;

    private Boolean havingPet;

    private int luxury;

    private Double gpa;

    private Integer rentingDuration;

    //***************************
    private Long customerId;
    //burası foreign key customer nesnesini setlemede kullanılacak

}

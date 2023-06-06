package com.example.newProject.DTOs.BasicDtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class HouseUpdateDto {


    private String houseAddress;

    private String houseType;

    private String heatResource;

    private String furnished;

    private String internetPaved;

    private int floor;

    private int rent;
}

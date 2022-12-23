package com.example.newProject.DTOs.BasicDtos;

import lombok.Data;

@Data
public class HouseOwnerRegisterDto {

    private String houseOwnerName;
    private String houseOwnerSurname;
    private String houseOwnerEmail;
    private String houseOwnerPassword;
    private String houseOwnerUsername;
    //-----------------------
    private Long houseId;

}

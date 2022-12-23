package com.example.newProject.DTOs.BasicDtos;

import lombok.Data;

@Data
public class CustomerRegisterDto {

    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPassword;
    private String customerUsername;

}

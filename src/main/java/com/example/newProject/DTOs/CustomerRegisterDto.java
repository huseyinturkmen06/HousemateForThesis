package com.example.newProject.DTOs;

import lombok.Data;

@Data
public class CustomerRegisterDto {

    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPassword;
    private String customerUsername;

}

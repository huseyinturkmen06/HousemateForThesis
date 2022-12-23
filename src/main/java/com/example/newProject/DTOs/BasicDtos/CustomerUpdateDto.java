package com.example.newProject.DTOs.BasicDtos;

import lombok.Data;

@Data
public class CustomerUpdateDto {

    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerUsername;
    private Integer customerAge;
    private String customerHometown;
    private String customerDepartment;
//    private String customerPassword;
    private Integer customerGrade;
    private String customerPhone;
    private String customerEmail;
    private String customerGender;

    //password farklı bir alanda update edileceğinden
    //bu dto da almadık

}

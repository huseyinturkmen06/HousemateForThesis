package com.example.newProject.DTOs.BasicDtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;

@Data
@Builder
@Getter
public class HouseOwnerResponse {

    private Long ownerId;

    private String ownerName;


    private String ownerSurname;


    private int ownerAge;


    private String ownerUsername;


    private String ownerPassword;


    private String ownerHometown;


    private int ownerGrade;


    private String ownerDepartment;


    private String ownerPhone;


    private String ownerMail;


    private String ownerGender;

    private long houseId;
}

package com.example.newProject.DTOs;

import lombok.Data;

@Data
public class HouseOwnerUpdateDto {

    private Long houseOwnerId;
    private String houseOwnerName;
    private String houseOwnerSurname;
    private String houseOwnerUsername;
    private Integer houseOwnerAge;
    private String houseOwnerHometown;
    private String houseOwnerDepartment;
//    private String houseOwnerPassword;
    private Integer houseOwnerGrade;
    private String houseOwnerPhone;
    private String houseOwnerEmail;
    private String houseOwnerGender;

    //password farklı bir alanda update edileceğinden
    //bu dto da almadık

}

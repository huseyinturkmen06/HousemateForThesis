package com.example.newProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Table(name="house_owners")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseOwner {

    @Id
    @GeneratedValue
    @Column(name="owner_id")
    private int ownerId;

    @Column(name="owner_name")
    private String ownerName;

    @Column(name="owner_surname")
    private String ownerSurname;

    @Column(name="owner_age")
    private int ownerAge;

    @Column(name="owner_hometown")
    private String ownerHometown;

    @Column(name="owner_grade")
    private String ownerGrade;

    @Column(name="owner_department")
    private String ownerDepartment;

    @Column(name="owner_phone")
    private String ownerPhone;

    @Column(name="owner_mail")
    private String ownerMail;

    @Column(name="owner_gender")
    private String ownerGender;

    @ManyToOne
    @JoinColumn(name="house_id")
    @JsonIgnore
    private House house;

}

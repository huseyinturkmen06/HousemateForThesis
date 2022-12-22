package com.example.newProject.Entities.ModelEntities;


import com.example.newProject.Entities.BasicEntities.House;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="model_of_house")
@Data
public class ModelAttributesOfHouse extends ModelAttributes{

    public ModelAttributesOfHouse() {
    }

    public ModelAttributesOfHouse(Long id, String ownerName, House house) {
        super(id, ownerName);
        this.house=house;
    }

    @JsonIgnore
    @JoinColumn(name="house_id")
    @OneToOne
    House house;

    @Column(name="class_of_house")
    private String classOfHouse;



}

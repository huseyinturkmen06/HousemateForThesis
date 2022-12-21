package com.example.newProject.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="model_of_house")
@Data
public class ModelAttributesOfHouse extends ModelAttributes{

    public ModelAttributesOfHouse() {
    }

    public ModelAttributesOfHouse(Long id, String ownerName,House house) {
        super(id, ownerName);
        this.house=house;
    }

    @JsonIgnore
    @JoinColumn(name="house_id")
    @OneToOne
    House house;



}

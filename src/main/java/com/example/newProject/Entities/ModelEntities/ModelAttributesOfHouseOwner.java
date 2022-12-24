package com.example.newProject.Entities.ModelEntities;


import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="model_of_house_owner")
@Data
public class ModelAttributesOfHouseOwner extends ModelAttributes {

    public ModelAttributesOfHouseOwner() {
    }

    public ModelAttributesOfHouseOwner(Long id, HouseOwner houseOwner) {
        super(id);
        this.houseOwner=houseOwner;
    }

//    @JsonIgnore
    @JoinColumn(name="house_owner_id")
    @OneToOne
//            (mappedBy = "houseOwnerModelAttribute")
            //üstteki satırı yazarsak bu tabloda houseowner id görünmüyor
    private HouseOwner houseOwner;


    @Column(name="class_of_houseOwner")
    private String classOfHouseOwner;

}

package com.example.newProject.api.controllers;


import com.example.newProject.Entities.HouseCustomer;
import com.example.newProject.Services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//aslında buraya pek ihtiyaç kalmayabilir
//çünkü bu entity nin metodları (service kısmındaki) diğer classlar içine dağılabilir

@RestController
@RequestMapping("/relations")
public class HavingRelationController {

    HouseService houseService;
    @Autowired
    public HavingRelationController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/getRalationsOfOneCustomer/{id}")
    public List<HouseCustomer> getRalationsOfOneCustomer(@PathVariable Long id){
        return houseService.getRalationsOfOneCustomer(id);
    }
}

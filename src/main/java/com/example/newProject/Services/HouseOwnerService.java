package com.example.newProject.Services;

import com.example.newProject.Entities.HouseOwner;
import com.example.newProject.Repositories.HouseOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseOwnerService {

    HouseOwnerRepository houseOwnerRepository;
    @Autowired
    public HouseOwnerService(HouseOwnerRepository houseOwnerRepository) {
        this.houseOwnerRepository = houseOwnerRepository;
    }

    //house owner ın evi var, evin de customer ları var
    //houseOwner ile customer arasında direkt bir ilişki yok



}

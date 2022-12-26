package com.example.newProject.DTOs.BasicDtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class HouseSaveDto {


//    private Long houseId;
    //kayıt yaparken id istemeyiz ama update için gerekli

    private String houseAddress;
    //adrese göre arama yapılacak ama şimdlik yalnızca string


    //******************************************
    //bunlar artık evin main entitysi içinde yok ancak ev kaydederken
    //evin luxury tablosuna da kayıt girmek gerektiği için
    //bunları da ilk kaydolan kullanıcıdan istiyorz

    private int countOfBathroom;

    private int countOfBedroom;
    //salon hariç tüm odalar bedroom olarak geçecek
    //çünkü zaten öğrenci evi

    private int countOfSalon;

    private int countOfOwner;

    //ancak bunlarla henüz bir setleme yapmadık

    //******************************************

//    private String houseType;
    //houseType ı burdan almıyoruz çünkü girilen yatak odası ve oturma odasından çekilcek

    private String heatResource;

    private String furnished;

    private String internetPaved;

    private int floor;

    private int rent;

}

package com.example.newProject.Entities.ModelEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//burası önemli!!
//
//evet  @Inheritance aanotasyonunu öğrendik ama
//o attribute ün adını değiştirmeyi de öğrenmemiz gerek (child class lar içinde)


@Entity
@Table(name="model_attributes")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ModelAttributes {
    //burası bizim main tablomuz ve main class ımız olacak ama,
    //eğer abstract yapsaydık burası için tablo oluşmazdı,
    //yalnız bunu extend eden class lar için tablo oluşurdu


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(name="main_atttribute")
//    private String ownerName;

    public ModelAttributes(Long id) {
        this.id = id;

    }

    @Column(name="sleep_time")
    private String sleepTime;
    //--------------------------------------      string


    //eğer evde 1 kişi bile sigara içerse sigara içilen ev olur (şimdilik)
    @Column(name="smoking")
    private Boolean smooking;
    //--------------------------------------      true or false

    //ev sahiplerinden 1 tanesinin bile evcil hayvanı varsa evin de having a pet i true olur
    @Column(name="having_pet")
    private Boolean havingPet;
    //--------------------------------------      true or false


    //bu luxury nin ev dışındaki entitylerde adının luxury care olması gerekir
    @Column(name="luxury")
    private int luxury;
    //--------------------------------------      1-10

    //evin gpa i içinde oturan kişilerin gpa i ortalaması olacak
    @Column(name="gpa")
    private Double gpa;
    //--------------------------------------      0-4.0

    //renting duration ev için, içinde oturan kişilerden renting durationu en yüksek olan, olacak
    @Column(name="renting_duration")
    private int rentingDuration;
    //--------------------------------------      6-96   example


    //bu sefer evin price ı yalnızca evin kendi fiyatından gelicek
    //yani içinde oturanların fiyat tercihlerinden gelmeyecek
    //burdan inherite ettikleri için houseOwnermodel ve customermodel larınd da price etiketleri olacak ama
    //onlar sadece ileride houseOwnerları da customer yaparız vs deiye kalabilir
    @Column(name="price")
    private int price;
    //burasın sonradan eklendi
    //--------------------------------------      3500   example


    //+++++++++++++++++++++++++++
    //house, customer ve houseOwner için class bulma işlemi hep aynı olacak çünkü
    //hepsinin model attributeleri aynı ve aynı attributeler ile oluşturulmuş ağa girecekler

    // 3 entity için de class diye bir attribute olacak
















}

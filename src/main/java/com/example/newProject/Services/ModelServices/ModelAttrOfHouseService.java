package com.example.newProject.Services.ModelServices;

import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Model_Integration.PredictionHandler;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseOwnerRepo;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseRepo;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ModelAttrOfHouseService {

    ModelAttrOfHouseOwnerRepo modelAttrOfHouseOwnerRepo;
    HouseOwnerRepository houseOwnerRepository;
    HouseRepository houseRepository;
    ModelAttrOfHouseRepo modelAttrOfHouseRepo;

    @Autowired
    public ModelAttrOfHouseService(ModelAttrOfHouseOwnerRepo modelAttrOfHouseOwnerRepo,
                                   HouseOwnerRepository houseOwnerRepository,
                                   HouseRepository houseRepository,
                                   ModelAttrOfHouseRepo modelAttrOfHouseRepo) {
        this.modelAttrOfHouseOwnerRepo = modelAttrOfHouseOwnerRepo;
        this.houseOwnerRepository=houseOwnerRepository;
        this.houseRepository=houseRepository;
        this.modelAttrOfHouseRepo=modelAttrOfHouseRepo;
    }


    //find all houses of one house
    static List<String> sleepTimes= new ArrayList<>();
    static List<Boolean> smookings= new ArrayList<>();
    static List<Boolean> havingPets= new ArrayList<>();
    static List<Integer> luxuries= new ArrayList<>();
    static List<Double> gpas= new ArrayList<>();
    static List<Integer> rentingDurations= new ArrayList<>();
    static List<Integer> prices = new ArrayList<>();


    public void fillAllListsOfOwnersOfTheHouse(List<HouseOwner> allOwnersOfHouse){
        //fill all lists with houseOwner attributes of the house
        //for its all houseOwners
        for(HouseOwner houseOwner:allOwnersOfHouse){
            //buraya geldiğimizde houseOwnerların attributeleri setlenmemiş oluyor
            //ama buraya gelmeden anket de doldurulmuş olunacağı için bu sefer nul gelmez

            ModelAttributesOfHouseOwner attribute=modelAttrOfHouseOwnerRepo.findByHouseOwner(houseOwner);
            //iterate every attributes of every owner

            sleepTimes.add(attribute.getSleepTime());
            smookings.add(attribute.getSmooking());
            havingPets.add(attribute.getSmooking());
            luxuries.add(attribute.getLuxury());
            gpas.add(attribute.getGpa());
            rentingDurations.add(attribute.getRentingDuration());
            prices.add(attribute.getPrice());
        }
    }


    public String calculateAverageSleepTimes(List<String> sleepTimes){
        LocalTime sum = LocalTime.MIN;
        for (String clock : sleepTimes) {
            sum = sum.plusHours(LocalTime.parse(clock).getHour())
                    .plusMinutes(LocalTime.parse(clock).getMinute());
        }
        int averageHour = sum.getHour() / sleepTimes.size();
        int averageMinute = sum.getMinute() / sleepTimes.size();
        return String.format("%02d:%02d", averageHour, averageMinute);
        //returns average of given times as "08:30", "09:45", "10:15"

    }

    public ModelAttributesOfHouse setAttributesOfOneHouseByOwners(Long houseId) throws JSONException, IOException, InterruptedException {


        House houseTemp = houseRepository.findById(houseId).orElse(null);
        //temporary houe

        List<HouseOwner> allOwnersOfHouse = houseOwnerRepository.findAllByHouse(houseTemp);
        //all owners of one house

        int countOfCustomerOfHouse=allOwnersOfHouse.size();
        //customer count of the house , use it very later

        fillAllListsOfOwnersOfTheHouse(allOwnersOfHouse);
        //filled all owner attributes list of the house

        //  PREPARING ATTRIBUTES OF HOUSE

        String ModelSleepTimeAttrOfHouse;
        Boolean ModelSmokingAttrOfHouse;
        Boolean ModelHavingPetAttrOfHouse;
        Integer ModelLuxuryAttrOfHouse;
        Double ModelGpaAttrOfHouse;
        Integer ModelRentingDurationAttrOfHouse;
        Integer ModelPriceAttrOfHouse;

        ModelSleepTimeAttrOfHouse= calculateAverageSleepTimes(ModelAttrOfHouseService.sleepTimes);

        if(smookings.contains(Boolean.TRUE))    ModelSmokingAttrOfHouse= Boolean.TRUE;
        else ModelSmokingAttrOfHouse= Boolean.FALSE;

        if(havingPets.contains(Boolean.TRUE)) ModelHavingPetAttrOfHouse= Boolean.TRUE;
        else ModelHavingPetAttrOfHouse= Boolean.FALSE;

        ModelLuxuryAttrOfHouse = (int) luxuries.stream().mapToDouble(d -> d).average().orElse(0.0);
        //lüxlik değerlerini listenin ortalaması yapıyoruz ama ileride değişir
        ModelGpaAttrOfHouse = gpas.stream().mapToDouble(d -> d).average().orElse(0.0);
        //burada hata alabiliriz ama incelenecek
        //ortalamaları da hepsinin ortralaması yapyoruz
        ModelRentingDurationAttrOfHouse = (int) rentingDurations.stream().mapToDouble(d -> d).average().orElse(0.0);
        ModelPriceAttrOfHouse = (int) prices.stream().mapToDouble(d -> d).average().orElse(0.0);
        //şimdlik price yerine price ların ortalamasını aldık

//***********************************
        //   CREATE HOUSE AND USE ABOVE


        ModelAttributesOfHouse modelAttributesOfHouse =  modelAttrOfHouseRepo.findByHouse(houseTemp);
        if(modelAttributesOfHouse==null){
            modelAttributesOfHouse=new ModelAttributesOfHouse();
        }
        //if a model attribute of hpuse entry found, use it,  else-> create new one

        modelAttributesOfHouse.setSleepTime(ModelSleepTimeAttrOfHouse);
        modelAttributesOfHouse.setSmooking(ModelSmokingAttrOfHouse);
        modelAttributesOfHouse.setHavingPet(ModelHavingPetAttrOfHouse);
        modelAttributesOfHouse.setLuxury(ModelLuxuryAttrOfHouse);
        modelAttributesOfHouse.setGpa(ModelGpaAttrOfHouse);
        modelAttributesOfHouse.setRentingDuration(ModelRentingDurationAttrOfHouse);

        modelAttributesOfHouse.setPrice(ModelPriceAttrOfHouse.intValue());


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        modelAttributesOfHouse.setPrice(3000);
        //burayı artık ortalamaya göre yazıyoruz
        //++++++++++++++++++++
        //normalde bir houseEklendiğinde otomatikmen Luxury attibute of house tablosuna da
        //house un model özellikleri eklenmeli ve sonra yeni kullanıcı eklenince burası güncellenmeli ama
        // (Luxury house ) tablosunda houseId ye uygun price çekilmeli ve
        //o price buraya verilmeli
        //şuan orası olmadığı için her yeni kaydolan evin price değerini 3000 yapıyoruz
        //yani buraso değişecek
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        //---------------------------
        modelAttributesOfHouse.setHouse(houseTemp);
        // use house as foreign key


        //USE VALUES AND MANIPULATE THEM FOR AI CLASS,  CONVERT THEM ALL TO STRING

        String sleepTime=modelAttributesOfHouse.getSleepTime();
        String smooking="";
        if(modelAttributesOfHouse.getSmooking()==true)  smooking="yes";
        else smooking="no";
        String havingAPet="";
        if(modelAttributesOfHouse.getHavingPet()==true) havingAPet="yes";
        else havingAPet="no";
        String luxuryCare=Integer.toString(modelAttributesOfHouse.getLuxury());
        String gpa=String.valueOf(modelAttributesOfHouse.getGpa());
        //age yerine şimdilik 0 veriyorum çünkü age bizim db de yok
        String age="0";
        String rentingDuration=Integer.toString(modelAttributesOfHouse.getRentingDuration());
        String price=Integer.toString(modelAttributesOfHouse.getPrice());
        //burası default 3000 geliyo ama değşmesi gerek

        // CALL MODEL FUNCTION FROM Prediction Handler

        String classOfHouse= PredictionHandler.ModelFunction(
                sleepTime,smooking,havingAPet,luxuryCare, gpa,age,rentingDuration,price);

        modelAttributesOfHouse.setClassOfHouse(classOfHouse);
        System.out.println("Evin class ı ::   "+classOfHouse);


        return modelAttrOfHouseRepo.save(modelAttributesOfHouse);

        //sonuç olarak bir ev id si verdiğimizde
        //houseOwners dan bu evlerin tüm ownerları bulunur
        //sonra bunların her birisinin attrof houseOwner attributelerinin ortalamalrı ile yeni attr ler ayarlanır
        //



        //bu metodun her yeni ownerkaydolduğunda yeniden çaışması ve evin model özellikleini güncellemesi gerekir
        //zaten her yeni houseowner kaydolurken houseId de veriyoduk,
        //aynı houseId ile bu fonksiyonu da çağırmamız yeterli olracaktır ama
        //houseOwner ın save işlemi tamamlandıktan sonra (save satırından sonra)
        //bu metod çalışmalı
    }











}

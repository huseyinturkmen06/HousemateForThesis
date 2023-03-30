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

    public ModelAttributesOfHouse setAttributesOfOneHouseByOwners(Long houseId) throws JSONException, IOException, InterruptedException {
        System.out.println(houseId);

        //find all houses of one house
        List<String> sleepTimes= new ArrayList<>();
        List<Boolean> smookings= new ArrayList<>();
        List<Boolean> havingPets= new ArrayList<>();
        List<Integer> luxuries= new ArrayList<>();
        List<Double> gpas= new ArrayList<>();
        List<Integer> rentingDurations= new ArrayList<>();
        List<Integer> prices = new ArrayList<>();

        House houseTemp = houseRepository.findById(houseId).orElse(null);
        //house u bulduk
        List<HouseOwner> allOwnersOfHouse = houseOwnerRepository.findAllByHouse(houseTemp);
        //1 house un tüm owner larını bulduk
//        System.out.println(allOwnersOfHouse.get(0).getOwnerName());

        //örneğin 1 id li evin tüm owner larını almış olduk

        int countOfCustomerOfHouse=allOwnersOfHouse.size();
        //burada bir evin kaç customer ı olduğunu görüyoruz ki bölme işlemini ona göre yapalım

        for(HouseOwner houseOwner:allOwnersOfHouse){
            //buraya geldiğimizde houseOwnerların attributeleri setlenmemiş oluyor
            //ama buraya gelmeden anket de doldurulmuş olunacağı için bu sefer nul gelmez

            ModelAttributesOfHouseOwner attribute=modelAttrOfHouseOwnerRepo.findByHouseOwner(houseOwner);
            System.out.println("--------------------");
            System.out.println(attribute.getGpa());
            //üstteki satır bize 1 kayıt döner ama içinde attributeleri var tabi
            //her bir owner ın model attributelerine erişiyoruz
            sleepTimes.add(attribute.getSleepTime());
            smookings.add(attribute.getSmooking());
            havingPets.add(attribute.getSmooking());
            luxuries.add(attribute.getLuxury());
            gpas.add(attribute.getGpa());
            rentingDurations.add(attribute.getRentingDuration());
            prices.add(attribute.getPrice());

            //1 evin tüm howner ları için listelere ekleme yaptık
        }


        //şimdlik sleep time attribute unu boş ayarlıyoruz!!!!!!!!!!!!
        //daha sonra nasıl tutulacağına karar veririz


        String ModelSleepTimeAttrOfHouse;
        Boolean ModelSmokingAttrOfHouse;
        Boolean ModelHavingPetAttrOfHouse;
        Double ModelLuxuryAttrOfHouse;
        Double ModelGpaAttrOfHouse;
        Double ModelRentingDurationAttrOfHouse;
        Double ModelPriceAttrOfHouse;


        ModelSleepTimeAttrOfHouse="22.00";


//***********************************
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        //şimdi setleme işlemlerini yapalım


        //saat setleme-----------------------------------------



        List<Integer> earlyTimes= new ArrayList<>();
        List<Integer> lateTimes= new ArrayList<>();
        //uyku zamanlarını ayarlayan int dizisi  22.00    06.10   00.00   6.10   00.00 18.30
        for(String temporarySleepTime:sleepTimes){
            if(temporarySleepTime.startsWith("0")){
                temporarySleepTime.replaceFirst("0","");
                //06.00 gibi girdilerden kurtulmak için
            }
            //0.00   22.00   6.10


            Integer ilkPartInt=   Integer.valueOf(temporarySleepTime.split("[.]")[0])  ;
//            System.out.println();
            if(ilkPartInt<=23 && ilkPartInt >=18){
                earlyTimes.add( Integer.valueOf(temporarySleepTime.split("[.]")[0])  );
            }
            else if(ilkPartInt<=7 && ilkPartInt>=0){
                lateTimes.add( Integer.valueOf(temporarySleepTime.split("[.]")[0])  );
            }
            else{
                ModelSleepTimeAttrOfHouse="22.00";
                //herhangi bir bloğa girmezse otomatik 22.00 a eşitledik
            }


            //şimdi bir evde oturanların yatma saatlerini late times ve early times a attık
        }

        if(lateTimes.size()>0){
            //1   2   3
            Integer intSaat= Collections.max(lateTimes);
            ModelSleepTimeAttrOfHouse= "0"+intSaat.toString()+"."+"00";
            //01.00
        }
        else {
            Integer intSaat= Collections.max(earlyTimes);
            ModelSleepTimeAttrOfHouse= intSaat.toString()+"."+"00";
        }


/////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////

        //--
        if(smookings.contains(Boolean.TRUE)){
            ModelSmokingAttrOfHouse= Boolean.TRUE;
        }
        else {
            ModelSmokingAttrOfHouse= Boolean.FALSE;
        }

        //--
        if(havingPets.contains(Boolean.TRUE)){
            ModelHavingPetAttrOfHouse= Boolean.TRUE;
        }
        else {
            ModelHavingPetAttrOfHouse= Boolean.FALSE;
        }

        //--
        ModelLuxuryAttrOfHouse = luxuries.stream().mapToDouble(d -> d).average().orElse(0.0);
        //double ama altta intValue(); yapıyorum
        //lüxlik değerlerini listenin ortalaması yapıyoruz ama ileride değişir


        //--
        ModelGpaAttrOfHouse = gpas.stream().mapToDouble(d -> d).average().orElse(0.0);
        //burada hata alabiliriz ama incelenecek
        //ortalamaları da hepsinin ortralaması yapyoruz

        //--
        ModelRentingDurationAttrOfHouse = rentingDurations.stream().mapToDouble(d -> d).average().orElse(0.0);
        //double ama altta intValue(); yapıyorum
        //renting durationslar da ortalama oluyor
        //burada double ama aşağıda int yaptım
        System.out.println("Renting duration: "+ ModelRentingDurationAttrOfHouse);


        ModelPriceAttrOfHouse = prices.stream().mapToDouble(d -> d).average().orElse(0.0);
        //şimdlik price yerine price ların ortalamasını aldık

//***********************************
        //evin tüm attributeleri hazırlandı, şimdi setleme zamanı
        //model attrıbutes of house ekleyeceiğimiz zaman zaten eklenecek houseId hazır olmalı
        //eğer model attributes of house tablosunda gereken house nesnesi ile bir kayıt varsa onu güncelle
        //yoksa yeni modelAttributesOfHouse nesnesi oluştur ve onun kayıtlarını gir


        ModelAttributesOfHouse modelAttributesOfHouse =  modelAttrOfHouseRepo.findByHouse(houseTemp);
        if(modelAttributesOfHouse==null){
            modelAttributesOfHouse=new ModelAttributesOfHouse();
        }
        //null gelmişse tabloda yok demektir ve yeni kayıt oluşur

        modelAttributesOfHouse.setSleepTime(ModelSleepTimeAttrOfHouse);
        //şimdlik böyle yaptık ama daha sonra mutlaka yukarıdakii fonksyionun içindeki değeri başta olmak üzere değişecektir
        modelAttributesOfHouse.setSmooking(ModelSmokingAttrOfHouse);
        modelAttributesOfHouse.setHavingPet(ModelHavingPetAttrOfHouse);
        modelAttributesOfHouse.setLuxury(ModelLuxuryAttrOfHouse.intValue());
        modelAttributesOfHouse.setGpa(ModelGpaAttrOfHouse);
        modelAttributesOfHouse.setRentingDuration(ModelRentingDurationAttrOfHouse.intValue());

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
        //methodun en başında el de ettiğimiz evi de foreign key olarak ayarladık ve en sağa kaydetiik


        //*********************************** model class ın ile haberleşme başlangıcı
//*********************************************************

        String sleepTime=modelAttributesOfHouse.getSleepTime();

        String smooking="";
        if(modelAttributesOfHouse.getSmooking()==true)  smooking="yes";
        else smooking="no";

        String havingAPet="";
        if(modelAttributesOfHouse.getHavingPet()==true) havingAPet="yes";
        else havingAPet="no";

        String luxuryCare=Integer.toString(modelAttributesOfHouse.getLuxury());

        String gpa=String. valueOf(modelAttributesOfHouse.getGpa());

        //age yerine şimdilik 0 veriyorum çünkü age bizim db de yok
        String age="0";

        String rentingDuration=Integer.toString(modelAttributesOfHouse.getRentingDuration());

        String price=Integer.toString(modelAttributesOfHouse.getPrice());
        //burası default 3000 geliyo ama değşmesi gerek
//*********************************************************************
//*********************************************************************

        //autowired hatasından dolayı methodu static yaptık ve öyle çağırdık
        //function call
        String classOfHouse= PredictionHandler.ModelFunction(
                sleepTime,smooking,havingAPet,luxuryCare, gpa,age,rentingDuration,price);

        //setting model class attribute to saving object
        modelAttributesOfHouse.setClassOfHouse(classOfHouse);
        System.out.println("Evin class ı ::   "+classOfHouse);


//buraya veri gelmiyor henüz

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

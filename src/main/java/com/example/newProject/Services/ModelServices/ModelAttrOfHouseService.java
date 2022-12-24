package com.example.newProject.Services.ModelServices;

import com.example.newProject.Entities.BasicEntities.House;
import com.example.newProject.Entities.BasicEntities.HouseOwner;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouse;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfHouseOwner;
import com.example.newProject.Repositories.BasicRepos.HouseOwnerRepository;
import com.example.newProject.Repositories.BasicRepos.HouseRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseOwnerRepo;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfHouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.ArrayList;
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

    public ModelAttributesOfHouse setAttributesOfOneHouseByOwners(Long houseId){

        //find all houses of one house
        List<String> sleepTimes= new ArrayList<>();
        List<Boolean> smookings= new ArrayList<>();
        List<Boolean> havingPets= new ArrayList<>();
        List<Integer> luxuries= new ArrayList<>();
        List<Double> gpas= new ArrayList<>();
        List<Integer> rentingDurations= new ArrayList<>();

        House houseTemp = houseRepository.findById(houseId).orElse(null);
        List<HouseOwner> allOwnersOfHouse = houseOwnerRepository.findAllByHouse(houseTemp);

        int countOfCustomerOfHouse=allOwnersOfHouse.size();
        //burada bir evin kaç customer ı olduğunu görüyoruz ki bölme işlemini ona göre yapalım

        for(HouseOwner houseOwner:allOwnersOfHouse){
            //buraya geldiğimizde houseOwnerların attributeleri setlenmemiş oluyor
            //ama buraya gelmeden anket de doldurulmuş olunacağı için bu sefer nul gelmez

            ModelAttributesOfHouseOwner attribue=modelAttrOfHouseOwnerRepo.findByHouseOwner(houseOwner);
            //üstteki satır bize 1 kayıt döner ama içinde attributeleri var tabi
            //her bir owner ın model attributelerine erişiyoruz
            sleepTimes.add(attribue.getSleepTime());
            smookings.add(attribue.getSmooking());
            havingPets.add(attribue.getSmooking());
            luxuries.add(attribue.getLuxury());
            gpas.add(attribue.getGpa());
            rentingDurations.add(attribue.getRentingDuration());
        }


        //şimdlik sleep time attribute unu boş ayarlıyoruz!!!!!!!!!!!!
        //daha sonra nasıl tutulacağına karar veririz


        String ModelSleepTimeAttrOfHouse;
        Boolean ModelSmokingAttrOfHouse;
        Boolean ModelHavingPetAttrOfHouse;
        Double ModelLuxuryAttrOfHouse;
        Double ModelGpaAttrOfHouse;
        Double ModelRentingDurationAttrOfHouse;

//***********************************
        //şimdi setleme işlemlerini yapalım

        //--
        ModelSleepTimeAttrOfHouse="";

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



        //--
        ModelGpaAttrOfHouse = gpas.stream().mapToDouble(d -> d).average().orElse(0.0);
        //burada hata alabiliriz ama incelenecek

        //--
        ModelRentingDurationAttrOfHouse = rentingDurations.stream().mapToDouble(d -> d).average().orElse(0.0);
        //double ama altta intValue(); yapıyorum

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
        //---------------------------
        modelAttributesOfHouse.setHouse(houseTemp);
        //methodun en başında el de ettiğimiz evi de foreign key olarak ayarladık ve en sağa kaydetiik

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

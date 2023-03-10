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

        House houseTemp = houseRepository.findById(houseId).orElse(null);
        List<HouseOwner> allOwnersOfHouse = houseOwnerRepository.findAllByHouse(houseTemp);
//        System.out.println(allOwnersOfHouse.get(0).getOwnerName());

        //??rne??in 1 id li evin t??m owner lar??n?? alm???? olduk

        int countOfCustomerOfHouse=allOwnersOfHouse.size();
        //burada bir evin ka?? customer ?? oldu??unu g??r??yoruz ki b??lme i??lemini ona g??re yapal??m

        for(HouseOwner houseOwner:allOwnersOfHouse){
            //buraya geldi??imizde houseOwnerlar??n attributeleri setlenmemi?? oluyor
            //ama buraya gelmeden anket de doldurulmu?? olunaca???? i??in bu sefer nul gelmez

            ModelAttributesOfHouseOwner attribute=modelAttrOfHouseOwnerRepo.findByHouseOwner(houseOwner);
            System.out.println("--------------------");
            System.out.println(attribute.getGpa());
            //??stteki sat??r bize 1 kay??t d??ner ama i??inde attributeleri var tabi
            //her bir owner ??n model attributelerine eri??iyoruz
            sleepTimes.add(attribute.getSleepTime());
            smookings.add(attribute.getSmooking());
            havingPets.add(attribute.getSmooking());
            luxuries.add(attribute.getLuxury());
            gpas.add(attribute.getGpa());
            rentingDurations.add(attribute.getRentingDuration());
        }


        //??imdlik sleep time attribute unu bo?? ayarl??yoruz!!!!!!!!!!!!
        //daha sonra nas??l tutulaca????na karar veririz


        String ModelSleepTimeAttrOfHouse;
        Boolean ModelSmokingAttrOfHouse;
        Boolean ModelHavingPetAttrOfHouse;
        Double ModelLuxuryAttrOfHouse;
        Double ModelGpaAttrOfHouse;
        Double ModelRentingDurationAttrOfHouse;

//***********************************
        //??imdi setleme i??lemlerini yapal??m

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
        //double ama altta intValue(); yap??yorum



        //--
        ModelGpaAttrOfHouse = gpas.stream().mapToDouble(d -> d).average().orElse(0.0);
        //burada hata alabiliriz ama incelenecek

        //--
        ModelRentingDurationAttrOfHouse = rentingDurations.stream().mapToDouble(d -> d).average().orElse(0.0);
        //double ama altta intValue(); yap??yorum

//***********************************
        //evin t??m attributeleri haz??rland??, ??imdi setleme zaman??
        //model attr??butes of house ekleyecei??imiz zaman zaten eklenecek houseId haz??r olmal??
        //e??er model attributes of house tablosunda gereken house nesnesi ile bir kay??t varsa onu g??ncelle
        //yoksa yeni modelAttributesOfHouse nesnesi olu??tur ve onun kay??tlar??n?? gir


        ModelAttributesOfHouse modelAttributesOfHouse =  modelAttrOfHouseRepo.findByHouse(houseTemp);
        if(modelAttributesOfHouse==null){
            modelAttributesOfHouse=new ModelAttributesOfHouse();
        }
        //null gelmi??se tabloda yok demektir ve yeni kay??t olu??ur

        modelAttributesOfHouse.setSleepTime(ModelSleepTimeAttrOfHouse);
        //??imdlik b??yle yapt??k ama daha sonra mutlaka yukar??dakii fonksyionun i??indeki de??eri ba??ta olmak ??zere de??i??ecektir
        modelAttributesOfHouse.setSmooking(ModelSmokingAttrOfHouse);
        modelAttributesOfHouse.setHavingPet(ModelHavingPetAttrOfHouse);
        modelAttributesOfHouse.setLuxury(ModelLuxuryAttrOfHouse.intValue());
        modelAttributesOfHouse.setGpa(ModelGpaAttrOfHouse);
        modelAttributesOfHouse.setRentingDuration(ModelRentingDurationAttrOfHouse.intValue());


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        modelAttributesOfHouse.setPrice(3000);
        //++++++++++++++++++++
        //normalde bir houseEklendi??inde otomatikmen Luxury attibute of house tablosuna da
        //house un model ??zellikleri eklenmeli ve sonra yeni kullan??c?? eklenince buras?? g??ncellenmeli ama
        // (Luxury house ) tablosunda houseId ye uygun price ??ekilmeli ve
        //o price buraya verilmeli
        //??uan oras?? olmad?????? i??in her yeni kaydolan evin price de??erini 3000 yap??yoruz
        //yani buraso de??i??ecek
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        //---------------------------
        modelAttributesOfHouse.setHouse(houseTemp);
        //methodun en ba????nda el de etti??imiz evi de foreign key olarak ayarlad??k ve en sa??a kaydetiik


        //*********************************** model class ??n ile haberle??me ba??lang??c??
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

        //age yerine ??imdilik 0 veriyorum ????nk?? age bizim db de yok
        String age="0";

        String rentingDuration=Integer.toString(modelAttributesOfHouse.getRentingDuration());

        String price=Integer.toString(modelAttributesOfHouse.getPrice());
//*********************************************************************
//*********************************************************************

        //autowired hatas??ndan dolay?? methodu static yapt??k ve ??yle ??a????rd??k
        //function call
        String classOfHouse= PredictionHandler.ModelFunction(
                sleepTime,smooking,havingAPet,luxuryCare, gpa,age,rentingDuration,price);

        //setting model class attribute to saving object
        modelAttributesOfHouse.setClassOfHouse(classOfHouse);


//buraya veri gelmiyor hen??z

        return modelAttrOfHouseRepo.save(modelAttributesOfHouse);

        //sonu?? olarak bir ev id si verdi??imizde
        //houseOwners dan bu evlerin t??m ownerlar?? bulunur
        //sonra bunlar??n her birisinin attrof houseOwner attributelerinin ortalamalr?? ile yeni attr ler ayarlan??r
        //



        //bu metodun her yeni ownerkaydoldu??unda yeniden ??a????mas?? ve evin model ??zellikleini g??ncellemesi gerekir
        //zaten her yeni houseowner kaydolurken houseId de veriyoduk,
        //ayn?? houseId ile bu fonksiyonu da ??a????rmam??z yeterli olracakt??r ama
        //houseOwner ??n save i??lemi tamamland??ktan sonra (save sat??r??ndan sonra)
        //bu metod ??al????mal??
    }











}

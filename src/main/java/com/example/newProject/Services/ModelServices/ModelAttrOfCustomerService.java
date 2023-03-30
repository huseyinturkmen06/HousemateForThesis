package com.example.newProject.Services.ModelServices;


import com.example.newProject.DTOs.BasicDtos.CustomerAttributeGetDto;
import com.example.newProject.DTOs.ModelDtos.ModelAttrOfCustomerDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfCustomer;
import com.example.newProject.Model_Integration.PredictionHandler;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfCustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

@Service
public class ModelAttrOfCustomerService {

    ModelAttrOfCustomerRepo modelAttrOfCustomerRepo;
    CustomerRepository customerRepository;
    PredictionHandler predictionHandler;
    //bunu autowired etmeyeceğimiz için constructor içine yazmadık
    //istersek setter yazabiliriz tabi


//    //second one
//    public ModelAttrOfCustomerService(PredictionHandler predictionHandler
//    ) {
//        this.predictionHandler=predictionHandler;
//
//    }

    @Autowired
    public ModelAttrOfCustomerService(ModelAttrOfCustomerRepo modelAttrOfCustomerRepo,
                                      CustomerRepository customerRepository
                                      ) {
        this.modelAttrOfCustomerRepo = modelAttrOfCustomerRepo;
        this.customerRepository=customerRepository;

    }



    public ModelAttributesOfCustomer getOneModelAttributeOfCustomer(CustomerAttributeGetDto customerAttributeGetDto){
        Long customerId= customerAttributeGetDto.getCustomerId();
        System.out.println(customerId);
        Customer customer = customerRepository.findById(customerId).orElse(null);
        ModelAttributesOfCustomer attributeToReturn=
                modelAttrOfCustomerRepo.findByCustomer(customer);
        return attributeToReturn;
        //attribute of customer döndük


    }





    //save or update OneModelAttrOfCustomer  (poll)
    public ModelAttributesOfCustomer saveOneCustomerAttribute(ModelAttrOfCustomerDto modelAttrOfCustomerDto) throws JSONException, IOException, InterruptedException {
        ModelAttributesOfCustomer attrOfCustomerToSave=
                modelAttrOfCustomerRepo.
                        findByCustomer(customerRepository.
                                findById(modelAttrOfCustomerDto.getCustomerId()).orElse(null));
        if(attrOfCustomerToSave==null){
            attrOfCustomerToSave=new ModelAttributesOfCustomer();
        }

        attrOfCustomerToSave.setSleepTime(modelAttrOfCustomerDto.getSleepTime());
        attrOfCustomerToSave.setPrice(modelAttrOfCustomerDto.getPrice());
        attrOfCustomerToSave.setSmooking(modelAttrOfCustomerDto.getSmooking());
        attrOfCustomerToSave.setHavingPet(modelAttrOfCustomerDto.getHavingPet());
        attrOfCustomerToSave.setLuxury(modelAttrOfCustomerDto.getLuxury());
        attrOfCustomerToSave.setGpa(modelAttrOfCustomerDto.getGpa());
        attrOfCustomerToSave.setRentingDuration(modelAttrOfCustomerDto.getRentingDuration());
        //****************
        Customer customerForForeignKey =
                customerRepository.findById(modelAttrOfCustomerDto.getCustomerId()).orElse(null);
        if(customerForForeignKey!=null){
            System.out.println("Service: Modele eklenecek customer bulundu");
            attrOfCustomerToSave.setCustomer(customerForForeignKey);
            //customer nesnesi de setlendi
            //burasında sade Long dönmek için bi değiştirdik
            //şuan henüz class ını tanımlamadık, ama tanımlayacağız

//*********************************** model class ın ile haberleşme başlangıcı
//*********************************************************

            String sleepTime=modelAttrOfCustomerDto.getSleepTime();

            String smooking="";
            if(modelAttrOfCustomerDto.getSmooking()==true)  smooking="yes";
            else smooking="no";

            String havingAPet="";
            if(modelAttrOfCustomerDto.getHavingPet()==true) havingAPet="yes";
            else havingAPet="no";

            String luxuryCare=Integer.toString(modelAttrOfCustomerDto.getLuxury());

            String gpa=String. valueOf(modelAttrOfCustomerDto.getGpa());

            //age yerine şimdilik 0 veriyorum çünkü age bizim db de yok
            String age="0";

            String rentingDuration=Integer.toString(modelAttrOfCustomerDto.getRentingDuration());

            String price=Integer.toString(modelAttrOfCustomerDto.getPrice());
//*********************************************************************
//*********************************************************************

            //autowired hatasından dolayı methodu static yaptık ve öyle çağırdık
            //function call
            String classOfCustomer= PredictionHandler.ModelFunction(
                    sleepTime,smooking,havingAPet,luxuryCare, gpa,age,rentingDuration,price);


//            public static String ModelFunction(String sleepTime,String smooking,String havingAPet,
//                    String luxuryCare,String gpa,String age,
//                    String rentingDuration,String price)

            //setting model class attribute to saving object
            attrOfCustomerToSave.setClassOfCustomer(classOfCustomer);

            return modelAttrOfCustomerRepo.save(attrOfCustomerToSave);
        }
        else{
            System.out.println("Girilen id ye ait kullanıcı bulunamadı");
            return null;
        }

    }


    //updateOnePollOfCustomer


}

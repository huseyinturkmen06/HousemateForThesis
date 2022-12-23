package com.example.newProject.Services.ModelServices;


import com.example.newProject.DTOs.ModelDtos.ModelAttrOfCustomerDto;
import com.example.newProject.Entities.BasicEntities.Customer;
import com.example.newProject.Entities.ModelEntities.ModelAttributesOfCustomer;
import com.example.newProject.Repositories.BasicRepos.CustomerRepository;
import com.example.newProject.Repositories.ModelRepos.ModelAttrOfCustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelAttrOfCustomerService {

    ModelAttrOfCustomerRepo modelAttrOfCustomerRepo;
    CustomerRepository customerRepository;
    @Autowired
    public ModelAttrOfCustomerService(ModelAttrOfCustomerRepo modelAttrOfCustomerRepo,
                                      CustomerRepository customerRepository) {
        this.modelAttrOfCustomerRepo = modelAttrOfCustomerRepo;
        this.customerRepository=customerRepository;
    }





    ////save one ModelAttribute of customer  (poll)
    public ModelAttributesOfCustomer saveOneCustomerAttribute(ModelAttrOfCustomerDto modelAttrOfCustomerDto){
        ModelAttributesOfCustomer attrOfCustomerToSave= new ModelAttributesOfCustomer();
        attrOfCustomerToSave.setSleepTime(modelAttrOfCustomerDto.getSleepTime());
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
            return modelAttrOfCustomerRepo.save(attrOfCustomerToSave);
        }
        else{
            System.out.println("Girilen id ye ait kullanıcı bulunamadı");
            return null;
        }

    }


    //updateOnePollOfCustomer


}

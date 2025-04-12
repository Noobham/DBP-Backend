package com.show.bookingApp.services;

import com.show.bookingApp.entity.ServiceEntity;
import com.show.bookingApp.repository.ServiceRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceLogic {

    ServiceRepo serviceRepo;

    public ServiceLogic(ServiceRepo serviceRepo){
        this.serviceRepo = serviceRepo;
    }

    public List<String> getAllCategory(){
        Iterator<ServiceEntity> serviceEntityIterator = serviceRepo.findAll().iterator();
        Set<String> setList = new HashSet<>();

        while(serviceEntityIterator.hasNext()){
            setList.add(serviceEntityIterator.next().getCategory());
        }
        List<String> categoryList = setList.stream().toList();
        return categoryList;
    }

    public List<ServiceEntity> getAllNamesByCategory(String category){
        Iterator<ServiceEntity> serviceEntityIterator = serviceRepo.findAll().iterator();
        List<ServiceEntity> setList = new ArrayList<>();

        while(serviceEntityIterator.hasNext()){
            ServiceEntity entity = serviceEntityIterator.next(); // Call next() only once
            if (entity.getCategory().equals(category) || category.equals("all")) {
                setList.add(entity); // Use the variable, not calling next() again
            }
        }
//        List<ServiceEntity> categoryList = setList.stream().toList();
        return setList;
    }

    public void saveService(ServiceEntity serviceEntity){
        serviceRepo.save(serviceEntity);
    }

    public ServiceEntity getServiceById(Integer id){
        return serviceRepo.findById(id).get();
    }
}

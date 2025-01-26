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

    public List<String> getAllNamesByCategory(String category){
        Iterator<ServiceEntity> serviceEntityIterator = serviceRepo.findAll().iterator();
        Set<String> setList = new HashSet<>();

        while(serviceEntityIterator.hasNext()){
            if(serviceEntityIterator.next().getCategory().equals(category)){
            setList.add(serviceEntityIterator.next().getName());
            }
        }
        List<String> categoryList = setList.stream().toList();
        return categoryList;
    }

    public void saveService(ServiceEntity serviceEntity){
        serviceRepo.save(serviceEntity);
    }
}

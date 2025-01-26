package com.show.bookingApp.controller;

import com.show.bookingApp.entity.ServiceEntity;
import com.show.bookingApp.services.ServiceLogic;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ServiceController {

    ServiceLogic serviceLogic;

    public ServiceController(ServiceLogic serviceLogic){
        this.serviceLogic = serviceLogic;
    }

    @GetMapping("/service/category")
    public List<String> getAllCategory(){
        return serviceLogic.getAllCategory();
    }

    @GetMapping("/service/category/{category}")
    public List<String> getAllCategoryByNames(@PathVariable String category){
        return serviceLogic.getAllNamesByCategory(category);
    }

    @PostMapping("/service")
    public String saveService(@RequestBody ServiceEntity serviceEntity){
        serviceLogic.saveService(serviceEntity);
        return "success";
    }
}

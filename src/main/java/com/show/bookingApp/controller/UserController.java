package com.show.bookingApp.controller;

import com.show.bookingApp.entity.UserEntity;
import com.show.bookingApp.repository.UserRepo;
import com.show.bookingApp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    private ResponseEntity<String> saveUser(@RequestBody UserEntity userEntity){
        try {
            userService.saveUser(userEntity);
            return  ResponseEntity.ok("User Created");
        }catch (Error e) {
            return  ResponseEntity.status(400).body("User not created" + e);
        }

    }
}

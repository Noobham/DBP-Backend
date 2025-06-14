package com.show.bookingApp.services;

import com.show.bookingApp.entity.UserEntity;
import com.show.bookingApp.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;
    private static final PasswordEncoder paswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void saveUser(UserEntity user){
        user.setUserPassword(paswordEncoder.encode(user.getUserPassword()));
        userRepo.save(user);
    }
}

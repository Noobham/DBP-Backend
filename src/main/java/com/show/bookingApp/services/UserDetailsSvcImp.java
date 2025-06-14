package com.show.bookingApp.services;

import com.show.bookingApp.entity.UserEntity;
import com.show.bookingApp.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsSvcImp implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsSvcImp.class);
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepo.findByUserName(username);
        if(user.isPresent()){
            UserEntity user1 = user.get();
            return User.builder()
                    .username(user1.getUserName())
                    .password(user1.getUserPassword())
                    .roles(user1.getUserRole())
                    .build();
        }
        throw new UsernameNotFoundException("User not found" + username);
    }
}

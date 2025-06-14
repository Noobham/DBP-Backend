package com.show.bookingApp.repository;

import com.show.bookingApp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<UserEntity , Integer> {
    Optional<UserEntity> findByUserName(String userName);
}

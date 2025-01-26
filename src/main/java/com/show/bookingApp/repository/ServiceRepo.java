package com.show.bookingApp.repository;
import com.show.bookingApp.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepo extends CrudRepository<ServiceEntity,Integer> {
}

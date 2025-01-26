package com.show.bookingApp.repository;

import com.show.bookingApp.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepo extends CrudRepository<BookingEntity,Integer> {
}

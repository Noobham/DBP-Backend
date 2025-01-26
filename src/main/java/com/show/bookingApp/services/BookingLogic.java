package com.show.bookingApp.services;

import com.show.bookingApp.entity.BookingEntity;
import com.show.bookingApp.repository.BookingRepo;
import org.springframework.stereotype.Service;

@Service
public class BookingLogic {

    BookingRepo bookingRepo;

    public BookingLogic(BookingRepo bookingRepo){
        this.bookingRepo = bookingRepo;
    }

    public void saveBooking(BookingEntity bookingEntity){
        bookingRepo.save(bookingEntity);
    }
}

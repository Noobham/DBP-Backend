package com.show.bookingApp.controller;


import com.show.bookingApp.entity.BookingEntity;
import com.show.bookingApp.services.BookingLogic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BookingController {

    BookingLogic bookingLogic;

    public BookingController(BookingLogic bookingLogic){
        this.bookingLogic = bookingLogic;
    }

    @PostMapping("/book")
    public String saveBooking(@RequestBody BookingEntity bookingEntity){
        bookingLogic.saveBooking(bookingEntity);
        return "success";
    }
}

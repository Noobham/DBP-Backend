package com.show.bookingApp.controller;


import com.show.bookingApp.entity.BookingEntity;
import com.show.bookingApp.services.BookingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public String saveBooking(@RequestBody BookingEntity bookingEntity){
        try{
            bookingService.saveBooking(bookingEntity);
            return "success";
        }
        catch (Error e){
            return e.toString();
        }
    }

    @GetMapping("/book")
    public List<BookingEntity> getBooking(){
        return bookingService.getBooking();
    }

    @PostMapping("/hello")
    public String printHello(){
        return "hello";
    }
}

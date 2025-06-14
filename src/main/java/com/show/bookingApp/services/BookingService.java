package com.show.bookingApp.services;

import com.show.bookingApp.entity.BookingEntity;
import com.show.bookingApp.entity.ServiceEntity;
import com.show.bookingApp.entity.UserEntity;
import com.show.bookingApp.repository.BookingRepo;
import com.show.bookingApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {


    BookingRepo bookingRepo;
    UserRepo userRepo;

    public BookingService(BookingRepo bookingRepo , UserRepo userRepo){
        this.userRepo = userRepo;
        this.bookingRepo = bookingRepo;
    }

    public void saveBooking(BookingEntity bookingEntity){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> user = userRepo.findByUserName(username);
        if(user.isPresent()) {
            bookingRepo.save(bookingEntity);
            user.get().addBookingId(bookingEntity.getId());
            userRepo.save(user.get());
        }
    }
    public List<BookingEntity> getBooking(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> user = userRepo.findByUserName(username);
        List<BookingEntity> bookingEntityList = new ArrayList<>();
        if(user.isPresent()){
            List<Integer> bookingList = user.get().getBookingId();
            for(Integer bookingId : bookingList){
                Optional<BookingEntity> booking = bookingRepo.findById(bookingId);
                booking.ifPresent(bookingEntityList::add);
            }
        }
        return bookingEntityList;
    }
    public List<BookingEntity> getAllBooking() {
        Iterator<BookingEntity> bookingEntitiesIterable = bookingRepo.findAll().iterator();
        List<BookingEntity> bookingEntityList = new ArrayList<>();

        while(bookingEntitiesIterable.hasNext()){
            BookingEntity entity = bookingEntitiesIterable.next(); // Call next() only once
                bookingEntityList.add(entity); // Use the variable, not calling next() again
        }
        return  bookingEntityList ;
    }
}

package com.show.bookingApp.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String userName;
    private String userPassword;
    private String userRole;

    @ElementCollection
    @CollectionTable(
            name = "user_booking_rel",
            joinColumns = @JoinColumn(name = "userId")
    )
    private List<Integer> BookingId = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String userName, String userPassword, String userRole , List<Integer> BookingId) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.BookingId = BookingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<Integer> getBookingId() {
        return BookingId;
    }

    public void setBookingIds(List<Integer> bookingIds) {
        this.BookingId = bookingIds;
    }

    public void addBookingId(Integer bookingId) {
        this.BookingId.add(bookingId);
    }

}

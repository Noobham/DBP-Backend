package com.show.bookingApp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Time;
import java.util.Date;


@Entity
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookingID;

    private Integer paymentId;

    private String status;

    private Integer serviceId;

    private Date date;
    private Time time;
}

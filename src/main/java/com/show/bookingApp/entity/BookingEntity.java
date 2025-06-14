package com.show.bookingApp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class BookingEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String orderID;

    private String paymentId;

    private String status;

    @ElementCollection
    @CollectionTable(
            name = "booking_service_rel",
            joinColumns = @JoinColumn(name = "bookingId")
    )
    private List<Integer> serviceId = new ArrayList<>();

    @CreatedDate
    private Date date;

    @CreationTimestamp
    private Time time;

    public BookingEntity() {
    }

    public BookingEntity(Integer id, String orderID, String paymentId, String status, List<Integer> serviceId, Date date, Time time) {
        this.id = id;
        this.orderID = orderID;
        this.paymentId = paymentId;
        this.status = status;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getServiceId() {
        return serviceId;
    }

    public void setServiceId(List<Integer> serviceId) {
        this.serviceId = serviceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}

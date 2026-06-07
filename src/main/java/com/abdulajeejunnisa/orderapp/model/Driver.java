package com.abdulajeejunnisa.orderapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String vehicleNumber;

    private boolean available = true;

    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Driver() {
    }

    public Driver(String name,
                  String email,
                  String phoneNo,
                  String vehicleNumber) {

        super(name, email, phoneNo);
        this.vehicleNumber = vehicleNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
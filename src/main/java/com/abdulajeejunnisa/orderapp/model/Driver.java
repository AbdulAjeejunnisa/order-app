package com.abdulajeejunnisa.orderapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String vehicleNumber;

    public Driver() {
    }

    public Driver(String name,
                  String email,
                  String phoneNo,
                  String vehicleNumber) {

        super(name, email, phoneNo);
        this.vehicleNumber = vehicleNumber;
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
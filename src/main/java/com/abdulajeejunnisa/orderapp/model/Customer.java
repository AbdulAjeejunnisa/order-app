package com.abdulajeejunnisa.orderapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String address;

    public Customer() {
    }

    public Customer(String name,
                    String email,
                    String phoneNo,
                    String address) {

        super(name, email, phoneNo);
        this.address = address;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
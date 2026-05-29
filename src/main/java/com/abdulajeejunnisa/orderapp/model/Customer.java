package com.abdulajeejunnisa.orderapp.model;

public class Customer extends User {
    private final String address;
    public Customer(
            int uid,
            String name,
            String email,
            String phoneNo,
            String address
    ) {

        super(uid, name, email, phoneNo);

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.address = address.trim();
    }
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

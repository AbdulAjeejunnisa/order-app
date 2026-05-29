package com.abdulajeejunnisa.orderapp.model;

public class Driver extends User{
    private final String vehicleNumber;
    public Driver( int uid, String name, String email, String phoneNo,String vehicleNumber) {
        super(uid, name, email, phoneNo);
        if (vehicleNumber == null ||
                vehicleNumber.isBlank()) {

            throw new IllegalArgumentException(
                    "Vehicle number cannot be empty"
            );
        }
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + getName() + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';

    }
}

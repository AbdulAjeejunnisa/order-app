package com.abdulajeejunnisa.orderapp.model;

public class Location {

    private final String city;
    private final String state;
    private final String country;
    private final String pincode;
    // Constructor
    

    public Location(String city, String state, String country, String pincode) {
        this.city = city.trim();
        this.state = state.trim();
        this.country = country.trim();
        this.pincode = pincode.trim();
    }
   @Override
    public String toString(){
        return city +", "+ pincode + " , "+ state + " ," + country ;
    }
}
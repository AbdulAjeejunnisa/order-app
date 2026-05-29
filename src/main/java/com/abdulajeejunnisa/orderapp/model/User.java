package com.abdulajeejunnisa.orderapp.model;

public class User {
    
    protected int uid;
    protected String name;
    protected String email;
    protected String phoneNo;
    public User(int uid, String name, String email, String phoneNo) {
        if(phoneNo == null || phoneNo.length() != 10){
            throw new IllegalArgumentException("Invalid Phone number ");
        }
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        
    }
    public int getUid() {
        return uid;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}

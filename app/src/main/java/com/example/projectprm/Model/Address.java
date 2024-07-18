package com.example.projectprm.Model;

import java.io.Serializable;

public class Address implements Serializable {
    private  String id;
    private String user_id;
    private String firstName;
    private String address;
    private String phoneNumber;

    public Address() {
    }

    public Address(String id, String firstName, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public Address(String user_id, String address) {
        this.user_id = user_id;
        this.address = address;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
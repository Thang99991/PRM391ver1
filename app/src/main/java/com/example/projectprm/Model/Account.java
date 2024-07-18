package com.example.projectprm.Model;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String registed_at;
    private String role;

    public Account(String id, String username, String password, String email, String phone, String registed_at, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.registed_at = registed_at;
        this.role = role;
    }

    public Account(String email, String password, String username, String phone) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisted_at() {
        return registed_at;
    }

    public void setRegisted_at(String registed_at) {
        this.registed_at = registed_at;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

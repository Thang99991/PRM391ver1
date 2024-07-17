package com.example.projectprm.Model;


public class LoginResponse {
    private Object token;
    private Account user;

    // Getters and setters

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}


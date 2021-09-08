package com.evaluate.evaluate.model;

public class Admin extends User{
    private int token;

    public Admin() {
    }

    public Admin(int token, int id, String email, String login, String password) {
        super(id, email, login, password);
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
    
    
}

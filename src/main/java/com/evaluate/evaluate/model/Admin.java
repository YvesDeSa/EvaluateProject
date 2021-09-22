package com.evaluate.evaluate.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends User{
    
    @Column(nullable = false, unique = false, updatable = true)
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

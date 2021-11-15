package com.evaluate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Admin extends User{
    
    @Column(nullable = false, unique = false, updatable = true)
    @NotNull(message = "token não pode ser nulo")
    private Long token;

    public Admin() {
    }

    public Admin(Long token, Long id, String email, String login, String password) {
        super(id, email, login, password);
        this.token = token;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }
 
}

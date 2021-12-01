package com.evaluate.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Admin extends UserApp{
    
    @Column(nullable = false, unique = false, updatable = true)
    @NotNull(message = "token não pode ser nulo")
    private Long token;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Admin deve ter no minimo 1 permissão")
    private List<Permit> permits = new ArrayList<>();


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
    
    public List<Permit> getPermits() {
        return permits;
    }

    public void setPermits(List<Permit> permits) {
        this.permits = permits;
    }
 
}

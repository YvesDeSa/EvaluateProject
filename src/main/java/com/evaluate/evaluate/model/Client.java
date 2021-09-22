package com.evaluate.evaluate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Client extends User{
    
    @Column(nullable = false, length = 55, unique = false, updatable = true)
    private String name;
    @Column(nullable = false, length = 55, unique = false, updatable = true)
    private String city;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "client", orphanRemoval = true)
    @JsonBackReference
    private List<Evaluation> evaluations = new ArrayList<>();

    public Client() {
    }

    public Client(String name, String city, int id, String email, String login, String password) {
        super(id, email, login, password);
        this.name = name;
        this.city = city;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
    
    
}

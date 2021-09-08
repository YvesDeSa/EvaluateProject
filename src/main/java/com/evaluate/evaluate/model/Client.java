package com.evaluate.evaluate.model;

import java.util.List;

public class Client extends User{
    private String name;
    private String city;
    
    private List<Comment> comments;
    private List<Evaluation> evaluations;

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
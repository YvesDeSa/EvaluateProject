package com.evaluate.evaluate.model;

import java.io.Serializable;
        
public class Comment implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String description;
    
    private Client client;
    private Evaluation evaluation;

    public Comment() {
    }

    public Comment(int id, String description, Client client, Evaluation evaluation) {
        this.id = id;
        this.description = description;
        this.client = client;
        
        this.evaluation = evaluation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        return this.id == other.id;
    }
    
}

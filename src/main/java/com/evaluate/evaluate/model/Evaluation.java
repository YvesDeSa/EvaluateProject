package com.evaluate.evaluate.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Evaluation implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String restaurantName;
    private String restaurantCategory;
    private String restaurantLocation;
    private Calendar date;
    private String nate;
    private String description;
   
    private Client client;
    private List<Comment> comments;

    public Evaluation() {
    }

    public Evaluation(int id, String restaurantName, String restaurantCategory, String restaurantLocation, Calendar date, String nate, String description, Client client) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantCategory = restaurantCategory;
        this.restaurantLocation = restaurantLocation;
        this.date = date;
        this.nate = nate;
        this.description = description;
        
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantCategory() {
        return restaurantCategory;
    }

    public void setRestaurantCategory(String restaurantCategory) {
        this.restaurantCategory = restaurantCategory;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getNate() {
        return nate;
    }

    public void setNate(String nate) {
        this.nate = nate;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
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
        final Evaluation other = (Evaluation) obj;
        return this.id == other.id;
    }
    
    
}

package com.evaluate.evaluate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluation implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50, unique = false, updatable = true)
    private String restaurantName;
    @Column(nullable = false, length = 20, unique = false, updatable = true)
    private String restaurantCategory;
    @Column(nullable = false, length = 75, unique = false, updatable = true)
    private String restaurantLocation;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar date;
    @Column(nullable = false, length = 15, unique = false, updatable = true)
    private String note;
    @Column(nullable = false, length = 256, unique = false, updatable = true)
    private String description;
    
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(nullable = false)
    private Client client;
    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "evaluation")
    @JsonBackReference
    private List<Comment> comments = new ArrayList<>();

    public Evaluation() {
    }

    public Evaluation(int id, String restaurantName, String restaurantCategory, String restaurantLocation, Calendar date, String note, String description, Client client) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantCategory = restaurantCategory;
        this.restaurantLocation = restaurantLocation;
        this.date = date;
        this.note = note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

package com.evaluate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evaluation implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 50, unique = false, updatable = true)
    @NotBlank(message = "Nome do restaurante obrigatorio")
    @Length(min = 2, max = 50 ,message = "O nome do restaurante deve ter entre 2 e 50 digitos.")
    private String restaurantName;
    
    @Column(nullable = false, length = 20, unique = false, updatable = true)
    @NotBlank(message = "Categoria do restaurante obrigatorio")
    @Length(min = 2, max = 20 ,message = "A categoria do restaurante deve ter entre 2 e 20 digitos.")
    private String restaurantCategory;
    
    @Column(nullable = false, length = 75, unique = false, updatable = true)
    @NotBlank(message = "Local do restaurante obrigatorio")
    @Length(min = 2, max = 75 ,message = "O local do restaurante deve ter entre 2 e 75 digitos.")
    private String restaurantLocation;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull(message = "data não pode ser nulo")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Calendar date;
    
    @Column(nullable = false, length = 15, unique = false, updatable = true)
    @NotBlank(message = "Nota é obrigatorio")
    @Length(min = 3, max = 15 ,message = "A nota deve ter entre 1 e 15 digitos.")
    private String note;
    
    @Column(nullable = false, length = 256, unique = false, updatable = true)
    @NotBlank(message = "Descrição é obrigatorio")
    @Length(min = 3, max = 256 ,message = "A descrição deve ter entre 2 e 50 digitos.")
    private String description;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @Valid
    @NotNull(message = "Cliente invalido")
    @JsonIgnore
    private Client client;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluation")
    private List<Comment> comments = new ArrayList<>();

    public Evaluation() {
    }

    public Evaluation(Long id, String restaurantName, String restaurantCategory, String restaurantLocation, Calendar date, String note, String description, Client client) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantCategory = restaurantCategory;
        this.restaurantLocation = restaurantLocation;
        this.date = date;
        this.note = note;
        this.description = description;
        
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        hash = (int) (89 * hash + this.id);
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
        return Objects.equals(this.id, other.id);
    }
     
}

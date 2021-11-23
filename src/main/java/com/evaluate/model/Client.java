package com.evaluate.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
public class Client extends User{
    
    @Column(nullable = false, length = 55, unique = false, updatable = true)
    @NotBlank(message = "Nome obrigatorio")
    @Length(min = 3, max = 55 ,message = "O nome deve ter entre 3 e 55 digitos.")
    private String name;
    
    @Column(nullable = false, length = 55, unique = false, updatable = true)
    @NotBlank(message = "Cidade obrigatorio")
    @Length(min = 3, max = 55 ,message = "A cidade deve ter entre 3 e 55 digitos.")
    private String city;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "client", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "client", orphanRemoval = true)
    private List<Evaluation> evaluations = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Cliente deve ter noi minimo 1 permissão")
    private List<Permit> permits;

    public Client() {
    }

    public Client(String name, String city, Long id, String email, String login, String password) {
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

    public List<Permit> getPermits() {
        return permits;
    }

    public void setPermits(List<Permit> permits) {
        this.permits = permits;
    }
    
}

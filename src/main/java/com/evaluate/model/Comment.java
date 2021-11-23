package com.evaluate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Comment implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 256, unique = false, updatable = true)
    @NotBlank(message = "Descrição obrigatorio")
    @Length(min = 3, max = 256 ,message = "A descrição deve ter entre 3 e 256 digitos.")
    private String description;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "cliente não pode ser nulo")
    @JsonIgnore
    private Client client;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "avaliação não pode ser nulo")
    @JsonIgnore
    private Evaluation evaluation;

    public Comment() {
    }

    public Comment(Long id, String description, Client client, Evaluation evaluation) {
        this.id = id;
        this.description = description;
        this.client = client;
        
        this.evaluation = evaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        hash = (int) (79 * hash + this.id);
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

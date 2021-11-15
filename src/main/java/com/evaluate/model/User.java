package com.evaluate.model;

import com.evaluate.annotation.EmailValidation;
import com.evaluate.annotation.LoginValidation;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 86, unique = false, updatable = true)
    @NotBlank(message = "Email obrigatorio")
    @EmailValidation(message = "Email é inválido")
    private String email;
    
    @Column(nullable = false, length = 25, unique = true, updatable = false)
    @NotBlank(message = "Login obrigatorio")
    @LoginValidation(message = "Login é Inválido")
    private String login;
    
    @Column(nullable = false, unique = false, updatable = true)
    @NotBlank(message = "senha obrigatorio")
    @Length(min = 3, max = 15 ,message = "A senha deve ter entre 3 e 15 digitos.")
    private String password;

    public User() {
    }

    public User(Long id, String email, String login, String password) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        long hash = 5;
        hash = 89 * hash + this.id;
        return (int) hash;
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
        final User other = (User) obj;
        return this.id == other.id;
    }  
    
}

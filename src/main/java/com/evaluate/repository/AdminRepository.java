package com.evaluate.repository;

import com.evaluate.model.Admin;
import com.evaluate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long>{
    public User findByEmail(String email);
    
    public User findByToken(Long token);
    
    public User findByLogin(String login);
}

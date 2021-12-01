package com.evaluate.repository;

import com.evaluate.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long>{
    public Admin findByEmail(String email);
    
    public Admin findByToken(Long token);
    
    public Admin findByLogin(String login);
}

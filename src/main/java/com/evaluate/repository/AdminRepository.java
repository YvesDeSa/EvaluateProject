package com.evaluate.repository;

import com.evaluate.model.Admin;
import com.evaluate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, Long>{
    public User findByToken(long token);
}

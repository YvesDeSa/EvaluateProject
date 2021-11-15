package com.evaluate.repository;

import com.evaluate.model.Client;
import com.evaluate.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
   
    public Client findByEmail(String email); 
    
    public Client findByLogin(String login);  
    
    @Query("SELECT c FROM Comment c WHERE c.client.id = :id")
    public List<Comment> findByComment(Long id);
}

package com.evaluate.repository;

import com.evaluate.model.Client;
import com.evaluate.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    @Query("SELECT c FROM Comment c WHERE c.id = :id")
    public Comment findById(@Param("id")long id);
    
    public Client findByClient(long clientId);
    
    public List<Comment> findByEvaluation(long evaluationId);
}

package com.evaluate.repository;

import com.evaluate.model.Comment;
import com.evaluate.model.Evaluation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{
    
    @Query("SELECT e FROM Evaluation e WHERE e.client = :client")
    public List<Evaluation> findByClient(@Param("client")long client);
    
    @Query("SELECT c FROM Comment c WHERE c.evaluation = :id")
    public List<Comment> findByComment(@Param("id")long id);
}

package com.evaluate.service;

import com.evaluate.exception.NotFoundException;
import com.evaluate.model.Comment;
import com.evaluate.repository.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repo;
    
    public List<Comment> findAll (int page, int size){
        Pageable p = (Pageable) PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Comment> findAll (){
        return (List<Comment>) repo.findAll();
    }
    
    public Comment findById(long id){
        Comment result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("admin não encontrado");
        }
        return result;
    }
    
    public Comment save(Comment c){
        try{
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Admn");
        }
    }
    
    public Comment update(Comment c){
        Comment obj = findById(c.getId());
        
        return repo.save(c);
    }
    
    public void delete(int id){
        Comment obj = findById(id);
        
        try{
            repo.delete(obj);
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar cliente");
        }
    }
}

package com.evaluate.service;

import com.evaluate.exception.NotFoundException;
import com.evaluate.model.Evaluation;
import com.evaluate.repository.EvaluationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository repo;
    
    public List<Evaluation> findAll (int page, int size){
        Pageable p = (Pageable) PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Evaluation> findAll (){
        return (List<Evaluation>) repo.findAll();
    }
    
    public Optional<Evaluation> findById(long id){
        Optional<Evaluation> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Avaliação não encontrado");
        }
        return result;
    }
    
    public Evaluation save(Evaluation c){
        try{
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Avaliação");
        }
    }
    
    public Evaluation update(Evaluation c){
        Optional<Evaluation> obj = findById(c.getId());
        return repo.save(c);
    }
    
    public void delete(int id){
        Optional<Evaluation> obj = findById(id);
        try{
            repo.delete(obj.get());
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar Avaliação");
        }
    }
}

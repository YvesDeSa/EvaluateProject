package com.evaluate.service;

import com.evaluate.exception.NotFoundException;
import com.evaluate.model.Client;
import com.evaluate.model.User;
import com.evaluate.repository.ClientRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repo;
    
    public List<Client> findAll (int page, int size){
        Pageable p = (Pageable) PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Client> findAll (){
        return (List<Client>) repo.findAll();
    }
    
    public Optional<Client> findById(long id){
        Optional<Client> result = repo.findById(id);
        
        if(result.isEmpty()){
            throw new NotFoundException("Cliente não encontrado");
        }
        return result;
    }
    
    public Client save(Client c){
        verificaLogin(c.getLogin(), c.getEmail());
        try{
            return repo.save(c);
        }catch(Exception e){
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao atualizar cliente.");
        }
    }
    
    public Client update(Client c){
        Optional<Client> obj = findById(c.getId());
        
        try{
            c.setId(obj.get().getId());
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar cliente");
        }
    }
    
    public void delete(long id){
        Optional<Client> obj = findById(id);
        
        try{
            repo.delete(obj.get());
        }catch(Exception e){
            throw new RuntimeException("Falha ao deletar cliente");
        }
    }
    
    private void verificaLogin(String login, String email){
        User result =  repo.findByLogin(login);
        if(result != null){
            throw new RuntimeException("Login ja cadastrado");
        }
         User resultEmail = repo.findByEmail(email);
        if(resultEmail != null){
            throw new RuntimeException("Email ja cadastrado");
        }  
    }
}

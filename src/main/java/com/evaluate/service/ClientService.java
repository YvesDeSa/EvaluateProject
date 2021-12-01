package com.evaluate.service;

import com.evaluate.exception.NotFoundException;
import com.evaluate.model.Client;
import com.evaluate.model.Permit;
import com.evaluate.model.UserApp;
import com.evaluate.repository.ClientRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    
    public Client findByEmail(String email){
        return repo.findByEmail(email);
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
        removeNullPermissions(c);
        
        try{
            c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
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
        
        removeNullPermissions(c);
        
        try{
            c.setId(obj.get().getId());
            c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
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
        UserApp result =  repo.findByLogin(login);
        if(result != null){
            throw new RuntimeException("Login ja cadastrado");
        }
         UserApp resultEmail = repo.findByEmail(email);
        if(resultEmail != null){
            throw new RuntimeException("Email ja cadastrado");
        }  
    }
    
    private void removeNullPermissions(Client c){
        c.getPermits().removeIf((Permit permit) -> {
            return permit.getId() == null;
        });
        if(c.getPermits().isEmpty()){
            throw new RuntimeException("Cliente precisa de pelo menos 1 permissão");
        }
    }
}

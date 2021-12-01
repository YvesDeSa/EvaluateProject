package com.evaluate.service;

import com.evaluate.exception.NotFoundException;
import com.evaluate.model.Admin;
import com.evaluate.model.UserApp;
import com.evaluate.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repo;
    
    public List<Admin> findAll (int page, int size){
        Pageable p = (Pageable) PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Admin> findAll (){
        return (List<Admin>) repo.findAll();
    }
    
    public Optional<Admin> findById(long id){
        Optional<Admin> result = repo.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("admin não encontrado");
        }
        return result;
    }
    
    public Admin save(Admin c){
        verificaLogin(c.getToken(), c.getLogin(), c.getEmail());
        try{
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao salvar Admn");
        }
    }
    
    public Admin update(Admin c){
        Optional<Admin> obj = findById(c.getId());
        
        try{
            c.setId(obj.get().getId());
            return repo.save(c);
        }catch(Exception e){
            throw new RuntimeException("Falha ao atualizar Admn");
        }
    }
    
    public void delete(long id){
        Optional<Admin> obj = findById(id);

        try{
            repo.delete(obj.get());
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

    private void verificaLogin(Long token, String login, String email){
        UserApp resultToken = repo.findByToken(token);
        if(resultToken != null){
            throw new RuntimeException("token ja cadastrado");
        }
        UserApp resultLogin = repo.findByLogin(login);
        if(resultLogin != null){
            throw new RuntimeException("login ja cadastrado");
        }
        UserApp resultEmail = repo.findByEmail(email);
        if(resultEmail != null){
            throw new RuntimeException("Email ja cadastrado");
        }   
    }
}



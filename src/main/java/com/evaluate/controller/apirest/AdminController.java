package com.evaluate.controller.apirest;

import com.evaluate.model.Admin;
import com.evaluate.service.AdminService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path =  "/apirest/admins")
public class AdminController {
    @Autowired
    private AdminService service;
    
    @GetMapping
    public ResponseEntity getAll( 
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Admin admin){    
        admin.setId(null);
        
        service.save(admin);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody Admin admin ){
        admin.setId(null);
        service.update(admin);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}

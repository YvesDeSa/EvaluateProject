package com.evaluate.controller.apirest;

import com.evaluate.model.Evaluation;
import com.evaluate.service.EvaluationService;
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
@RequestMapping(path =  "/apirest/evaluations")
public class EvaluationController {
    @Autowired
    private EvaluationService service;
    
    @GetMapping
    public ResponseEntity getAll( 
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){

        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") int id){
        
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Evaluation evalution){
        evalution.setId(null);
        
        service.save(evalution);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(evalution);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @Valid @RequestBody Evaluation evalution ){
        evalution.setId(null);
        service.update(evalution);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
}

package com.evaluate.controller;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.evaluate.exception.Error;
import com.evaluate.exception.NotFoundException;
import com.evaluate.exception.PropertyError;
import com.evaluate.exception.ValidatioError;
import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class MyRestControlllerAdvice {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erroValidacao(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidatioError erro = new ValidatioError(
                Calendar.getInstance(), 
                HttpStatus.UNPROCESSABLE_ENTITY.value(), 
                HttpStatus.UNPROCESSABLE_ENTITY.name(), 
                "Erro de validação", 
                request.getRequestURI());
        
        e.getBindingResult().getFieldErrors().stream().map(fe -> new PropertyError(fe.getField(), fe.getDefaultMessage())).forEachOrdered(p -> {
            erro.getErrors().add(p);
        });
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity erroValidacao(ConstraintViolationException e, HttpServletRequest request){
        ValidatioError erro = new ValidatioError(
                Calendar.getInstance(), 
                HttpStatus.UNPROCESSABLE_ENTITY.value(), 
                HttpStatus.UNPROCESSABLE_ENTITY.name(), 
                "Erro de validação", 
                request.getRequestURI());
        
        e.getConstraintViolations().stream().map(cv -> new PropertyError(cv.getPropertyPath().toString(), cv.getMessage())).forEachOrdered(p -> {
            erro.getErrors().add(p);
        });
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity erroPadrao(Exception e, HttpServletRequest request){
        Error erro = new Error(
                Calendar.getInstance(), 
                HttpStatus.BAD_REQUEST.value(), 
                HttpStatus.BAD_REQUEST.name(), 
                e.getMessage(), 
                request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity erroPadrao(NotFoundException e, HttpServletRequest request){
        Error erro = new Error(
                Calendar.getInstance(), 
                HttpStatus.NOT_FOUND.value(), 
                HttpStatus.NOT_FOUND.name(), 
                e.getMessage(), 
                request.getRequestURI());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
}

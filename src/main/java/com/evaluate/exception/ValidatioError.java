package com.evaluate.exception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ValidatioError extends Error{
    
    private List<PropertyError> errors = new ArrayList<>();
    
    public ValidatioError(Calendar timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<PropertyError> getErrors() {
        return errors;
    }

    public void setErrors(List<PropertyError> errors) {
        this.errors = errors;
    }
    
}

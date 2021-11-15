package com.evaluate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class LoginValidator implements ConstraintValidator<LoginValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        if(value == null) return false;
        if(value.length() < 3) return false;
        if(value.length() > 25) return false;
        return true;
    }
    
}

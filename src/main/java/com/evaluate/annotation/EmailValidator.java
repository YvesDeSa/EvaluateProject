
package com.evaluate.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<EmailValidation, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        if(value == null) return false;
        if(value.contains(" ")) return false;
        return value.matches("[\\w\\S]+[@]+[\\w\\S]+[.]+[\\w\\S]+");
    }
    
}

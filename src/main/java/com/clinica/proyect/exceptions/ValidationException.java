package com.clinica.proyect.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ValidationException extends RuntimeException{

    private final BindingResult bindingResult;

    public ValidationException(String message, BindingResult bindingResult){
        super(message);
        this.bindingResult = bindingResult;
    }

    public Map<String,String> getErrors(){
        Map<String,String> errors = new HashMap<>();
        for(FieldError error : this.bindingResult.getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return errors;
    }
}

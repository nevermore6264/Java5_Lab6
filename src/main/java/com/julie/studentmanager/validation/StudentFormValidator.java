package com.julie.studentmanager.validation;

import com.julie.studentmanager.domain.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String[] arrayOfFields = new String[] {"secondName","firstName","group","entranceDate"};

        for(String elem: arrayOfFields){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, elem, "required", "Поля не должны быть пустыми!");
            if(errors.hasErrors()) break;
        }
    }
}


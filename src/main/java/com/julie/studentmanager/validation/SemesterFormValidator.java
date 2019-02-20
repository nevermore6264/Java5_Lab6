package com.julie.studentmanager.validation;

import com.julie.studentmanager.domain.Semester;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SemesterFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return Semester.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        String textError = "Поля не должны быть пустыми!";
        String[] arrayOfFields = new String[] {"name","duration","disciplineList"};

        for(String elem: arrayOfFields){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, elem, "required." + elem, textError);
            if(errors.hasErrors()){break;}
        }
    }
}

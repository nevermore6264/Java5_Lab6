package com.julie.studentmanager.validation;

import com.julie.studentmanager.domain.Discipline;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DisciplineFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Discipline.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String textError = "Поля не должны быть пустыми!";
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", textError);
    }
}

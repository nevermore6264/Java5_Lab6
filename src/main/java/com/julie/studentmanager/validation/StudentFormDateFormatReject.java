package com.julie.studentmanager.validation;

import com.julie.studentmanager.domain.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentFormDateFormatReject implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.invokeValidator(new StudentFormValidator(), o, errors);
        errors.rejectValue("entranceDate", "format.error", "Неправильный формат даты");

    }
}

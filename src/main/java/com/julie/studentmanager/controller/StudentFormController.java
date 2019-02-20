package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Student;
import com.julie.studentmanager.repository.StudentRepository;
import com.julie.studentmanager.validation.StudentFormDateFormatReject;
import com.julie.studentmanager.validation.StudentFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StudentFormController {

    private StudentRepository studentRepository;
    private StudentFormValidator studentFormValidator;
    private StudentFormDateFormatReject studentFormDateFormatReject;

    @Autowired
    private StudentFormController(StudentRepository studentRepository, StudentFormValidator studentFormValidator, StudentFormDateFormatReject studentFormDateFormatReject) {
        this.studentRepository = studentRepository;
        this.studentFormValidator = studentFormValidator;
        this.studentFormDateFormatReject = studentFormDateFormatReject;
    }

    @InitBinder
    protected void initStudentFormBinder(final WebDataBinder binder)
    {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateObj = format.parse(text);
                    setValue(dateObj);
                    binder.setValidator(studentFormValidator);
                } catch (ParseException e1) {
                    binder.setValidator(studentFormDateFormatReject);
                }
            }
        });
    }

    @RequestMapping(value = "/submitStudentAdd", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student")@Validated Student student,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "studentForm";

        this.studentRepository.setStudent(student);

        return "redirect:/students";
    }

    @RequestMapping(value = "/submitStudentUpdate", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("student")@Validated Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "studentForm";

        this.studentRepository.updateStudent(student);

        return "redirect:students";
    }
}

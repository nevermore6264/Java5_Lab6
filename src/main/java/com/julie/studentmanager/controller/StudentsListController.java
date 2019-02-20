package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Student;
import com.julie.studentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentsListController {

    private StudentRepository studentRepository;

    @Autowired
    private StudentsListController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents(Model model, @RequestParam(value = "error", required = false) String error) {
        List<Student> studentList = this.studentRepository.getStudentList();

        if(studentList.size() == 0) {
            model.addAttribute("info", "В базе данных студенты отсутствуют");
        }
        if(null != error) model.addAttribute("error", "Выберите студента");

        model.addAttribute("students", studentList);
        return "studentsList";
    }
}

package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Student;
import com.julie.studentmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StudentsListButtonsController {

    private StudentRepository studentRepository;

    @Autowired
    private StudentsListButtonsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());

        return "studentForm";
    }

    @RequestMapping(value = "/editStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String editStudent(@RequestParam(value = "idStud",  required = false)Integer id, Model model){
        if(null == id) return "redirect:students?error=true";

        Student student = this.studentRepository.getStudentById(id);

        model.addAttribute("student", student);
        return "studentForm";
    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String deleteStudent(@RequestParam(value = "idStud", required = false)Integer id) {
        if (null == id) return "redirect:students?error=true";
        this.studentRepository.removeStudent(id);
        return "redirect:students";
    }
}

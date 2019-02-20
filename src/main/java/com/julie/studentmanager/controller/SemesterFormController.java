package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.domain.Semester;
import com.julie.studentmanager.repository.SemesterRepository;
import com.julie.studentmanager.validation.SemesterFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;

@Controller
public class SemesterFormController {

    private SemesterRepository semesterRepository;
    private SemesterFormValidator semesterFormValidator;

    @Autowired
    public SemesterFormController(SemesterRepository semesterRepository, SemesterFormValidator semesterFormValidator) {
        this.semesterRepository = semesterRepository;
        this.semesterFormValidator = semesterFormValidator;
    }

    @InitBinder
    protected void initSemesterFormBinder(final WebDataBinder binder)
    {
        binder.setValidator(semesterFormValidator);
    }

    @RequestMapping(value = "/submitSemesterAdd", method = RequestMethod.POST)
    public String submitAddSemester(@ModelAttribute("semester") @Validated Semester semester,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {

            Set<Discipline> disciplineList = new HashSet<Discipline>(this.semesterRepository.getDisciplineList());
            model.addAttribute("disciplines",disciplineList);

            return "semesterForm";
        }

        this.semesterRepository.setSemester(semester, semester.getDisciplineList());

        return "redirect:semesters";
    }


    @RequestMapping(value = "/submitSemesterUpdate", method = RequestMethod.POST)
    public String submitUpdateSemester(@ModelAttribute("semester") @Validated Semester semester,
                                       BindingResult bindingResult,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            Set<String> selectedDisciplineNames = new HashSet<String>(this.semesterRepository.getSelectedDisciplineName(semester.getId()));
            Set<Discipline> disciplineList = new HashSet<Discipline>(this.semesterRepository.getDisciplineList());

            model.addAttribute("selected",selectedDisciplineNames);
            model.addAttribute("disciplines",disciplineList);

            return "semesterForm";
        }

        this.semesterRepository.updateSemester(semester, semester.getId(), semester.getDisciplineList());

        return "redirect:semesters";
    }
}

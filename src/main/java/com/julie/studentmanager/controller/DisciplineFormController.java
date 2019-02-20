package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.repository.DisciplineRepository;
import com.julie.studentmanager.validation.DisciplineFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DisciplineFormController {

    private DisciplineRepository disciplineRepository;
    private DisciplineFormValidator disciplineFormValidator;


    @Autowired
    private DisciplineFormController(DisciplineRepository disciplineRepository, DisciplineFormValidator disciplineFormValidator){
        this.disciplineRepository = disciplineRepository;
        this.disciplineFormValidator = disciplineFormValidator;
    }

    @InitBinder
    protected void initDisciplineFormBinder(final WebDataBinder binder)
    {
        binder.setValidator(disciplineFormValidator);
    }


    @RequestMapping(value = "/submitDisciplineAdd", method = RequestMethod.POST)
    public String submitAddDiscipline(@ModelAttribute("discipline") @Validated Discipline discipline, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "disciplineForm";

        this.disciplineRepository.setDiscipline(discipline);

        return "redirect:disciplines";
    }

    @RequestMapping(value = "/submitDisciplineUpdate", method = RequestMethod.POST)
    public String submitUpdateDiscipline(@ModelAttribute("discipline") @Validated Discipline discipline, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "disciplineForm";

        this.disciplineRepository.updateDiscipline(discipline);

        return "redirect:disciplines";
    }
}

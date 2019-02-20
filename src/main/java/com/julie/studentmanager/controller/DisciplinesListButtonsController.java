package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DisciplinesListButtonsController {

    private DisciplineRepository disciplineRepository;

    @Autowired
    private DisciplinesListButtonsController (DisciplineRepository disciplineRepository){
        this.disciplineRepository = disciplineRepository;
    }

    @RequestMapping(value = "/addDiscipline", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String addDiscipline(Model model){
        model.addAttribute("discipline", new Discipline());

        return "disciplineForm";
    }

    @RequestMapping(value = "/editDiscipline", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String editDisicpline(@RequestParam(value = "idDiscipline", required = false)Integer id, Model model){
        if(null == id) return "redirect:disciplines?error=true";

        Discipline discipline = this.disciplineRepository.getDisciplineById(id);

        model.addAttribute("discipline", discipline);

        return "disciplineForm";
    }

    @RequestMapping(value = "/deleteDiscipline", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String deleteDiscipline(@RequestParam(value = "idDiscipline", required = false)Integer id){
        if(null == id) return "redirect:disciplines?error=true";

        this.disciplineRepository.removeDiscipline(id);

        return "redirect:disciplines";
    }
}

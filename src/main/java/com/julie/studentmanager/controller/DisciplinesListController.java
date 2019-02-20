package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class DisciplinesListController {

    private DisciplineRepository disciplineRepository;


    @Autowired
    private DisciplinesListController(DisciplineRepository disciplineRepository){
        this.disciplineRepository = disciplineRepository;
    }

    @RequestMapping(value = "/disciplines", method = RequestMethod.GET)
    public String getDisciplinesList(Model model, @RequestParam(value = "error", required = false) String error){
        Set<Discipline> disciplineList = new HashSet<Discipline>(this.disciplineRepository.getDisciplineList());

        if(disciplineList.size() == 0) {
            model.addAttribute("info", "В базе данных информация о предметах отсутствует");
        }

        if(null != error) model.addAttribute("error", "Выберите предмет");

        model.addAttribute("disciplines", disciplineList);
        return "disciplinesList";
    }
}

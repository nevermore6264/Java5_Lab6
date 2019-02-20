package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.domain.Semester;
import com.julie.studentmanager.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SemestersListButtonsController {

    private SemesterRepository semesterRepository;

    @Autowired
    public SemestersListButtonsController(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @RequestMapping(value = "/addSemester", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String addSemester(Model model){
        Set<Discipline> disciplineList = new HashSet<Discipline>(this.semesterRepository.getDisciplineList());

        model.addAttribute("semester", new Semester());
        model.addAttribute("disciplines", disciplineList);

        return "semesterForm";
    }

    @RequestMapping(value = "/editSemester", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String editSemester(@RequestParam(value = "idSem", required = false)Integer id, Model model){

        if(null == id) return "redirect:semesters?error=true";

        Semester semester = this.semesterRepository.getSemesterByIdWithDiscipl(id);
        List<Discipline> disciplineListBySemId = this.semesterRepository.getDisciplineListBySemId(id);
        Set<String> selected = new HashSet<String>();

        for(Discipline elem: disciplineListBySemId){
            selected.add(elem.getName());
        }
        Set<Discipline> disciplineList = new HashSet<Discipline>(this.semesterRepository.getDisciplineList());

        model.addAttribute("semester", semester);
        model.addAttribute("selected", selected);
        model.addAttribute("disciplines", disciplineList);
        return "semesterForm";
    }

    @RequestMapping(value = "/deleteSemester", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String deleteSemester(@RequestParam("idSem") Integer id){
        if(null == id) return "redirect:semesters?error=true";

        this.semesterRepository.removeSemester(id);

        return "redirect:semesters";
    }
}

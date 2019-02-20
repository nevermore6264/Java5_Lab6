package com.julie.studentmanager.controller;

import com.julie.studentmanager.domain.Semester;
import com.julie.studentmanager.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SemestersListController {

    private SemesterRepository semesterRepository;

    @Autowired
    public SemestersListController(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @RequestMapping(value = "/semesters", method = RequestMethod.GET)
    public String  semesterList(@RequestParam(value = "idSem", required = false) Integer idSem,
                                @RequestParam(value = "error", required = false) String error,
                                Model model) {

        List<Semester> semesterList = this.semesterRepository.getSemesterList();

        if(semesterList.size() != 0) {
            if(null != error) model.addAttribute("error", "Выберите семестр");

            Semester semester;

            if(null == idSem) semester = this.semesterRepository.getSemesterByIdWithDiscipl(semesterList.get(0).getId());
            else semester = this.semesterRepository.getSemesterByIdWithDiscipl(idSem);

            String nedeli = (null == semester) ? "недель" : this.semesterRepository.selectWordNedeli(semester.getDuration());

            model.addAttribute("nedeli",nedeli);
            model.addAttribute("semesters",semesterList);
            model.addAttribute("selectedSemester",semester);

            return "semestersList";
        }else{
            model.addAttribute("info", "В базе данных информации о семестрах отсутствует");
            return "semestersList";
        }
    }
}


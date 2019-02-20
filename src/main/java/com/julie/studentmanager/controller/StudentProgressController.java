package com.julie.studentmanager.controller;


import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.domain.Progress;
import com.julie.studentmanager.domain.Semester;
import com.julie.studentmanager.domain.Student;
import com.julie.studentmanager.repository.StudentProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class StudentProgressController {

    private StudentProgressRepository studentProgressRepository;

    @Autowired
    public StudentProgressController(StudentProgressRepository studentProgressRepository) {
        this.studentProgressRepository = studentProgressRepository;
    }

    @RequestMapping(value="/progress", method = RequestMethod.GET)
    public String getStudentProgress(@RequestParam(value = "idStud", required = false) Integer idStud,
                                     @RequestParam(value="idSem", required = false) Integer idSem,
                                     @RequestParam(value = "error", required = false) String error,
                                     Model model){

        if(null == idStud) return "redirect:students?error=true";

        Student student = this.studentProgressRepository.getStudentById(idStud);

        model.addAttribute("student",student);

        List<Semester> semestersList = this.studentProgressRepository.getSemesterList();
        if(semestersList.size() != 0){

            Semester semester;

            if(null == idSem) semester = this.studentProgressRepository.getSemesterById(semestersList.get(0).getId());
            else semester = this.studentProgressRepository.getSemesterById(idSem);

            Map<Discipline, Integer> studentDisciplinePointMap =
                            this.studentProgressRepository.getProgressListByStudIdAndSemId(student.getId(), semester.getId());

            if(studentDisciplinePointMap.size() != 0){
                double avaragePoint = this.studentProgressRepository.getAvaragePointOfStudentProgress(student.getId(), semester.getId());
                String wordBally = (avaragePoint == 1) ? "балл"
                                    : (avaragePoint == 5) ? "баллов"
                                    : "балла";
                model.addAttribute("avaragePoint", avaragePoint);
                model.addAttribute("wordBally", wordBally);
            }

            model.addAttribute("semesters",semestersList);
            model.addAttribute("selectedSemester",semester);
            model.addAttribute("progressPoints", studentDisciplinePointMap);
        }else{
            model.addAttribute("info", "В базе данных информации о семестрах отсутствует");
        }

        return "studentProgress";
    }

    @RequestMapping(value = "/addProgress", method = RequestMethod.GET)
    @PreAuthorize("hasRose('admin')")
    public String addProgress(@RequestParam("idStud") int idStud,
                              @RequestParam("idSem") int idSem, Model model){
//        List<Student> studentsList = this.studentProgressRepository.getStudentList();
        Student student = this.studentProgressRepository.getStudentById(idStud);
        List<Discipline> disciplineList = this.studentProgressRepository.getDisciplineListBySemId(idSem);
        Semester semester = this.studentProgressRepository.getSemesterById(idSem);

            model.addAttribute("semester",semester);
            model.addAttribute("progress", new Progress());
            model.addAttribute("student", student);
            model.addAttribute("disciplineList", disciplineList);
        return "progressForm";
    }

    @RequestMapping(value = "/submitProgressAdd", method = RequestMethod.POST)
    public String setProgress(@RequestParam("idSem") int idSem,@ModelAttribute("progress")Progress progress ){
        this.studentProgressRepository.setStudentProgress(progress);

        int idStud = progress.getStudent().getId();
        return "redirect:progress?idStud="+idStud+"&idSem="+idSem;
    }

}

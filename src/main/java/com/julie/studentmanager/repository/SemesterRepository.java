package com.julie.studentmanager.repository;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.domain.Progress;
import com.julie.studentmanager.domain.Semester;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class SemesterRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected List<Discipline> refactorDisciplineList(List<Discipline> disciplineList){
        List<Discipline> allDisciplines = new ArrayList<Discipline>();
        for (Discipline elem: disciplineList){
            Integer idDisc = Integer.parseInt(elem.getName());
            Discipline d = (Discipline)this.sessionFactory.getCurrentSession().createCriteria(Discipline.class)
                    .add(Restrictions.idEq(idDisc)).addOrder(Order.asc("semester")).uniqueResult();
            allDisciplines.add(d);
        }
        return allDisciplines;
    }

    public void setSemester(Semester semester, List<Discipline> disciplineList){
        List<Discipline> allDisciplines = refactorDisciplineList(disciplineList);
        semester.setDisciplineList(allDisciplines);
        this.sessionFactory.getCurrentSession().saveOrUpdate(semester);
    }

    public void updateSemester(Semester semester, Integer id, List<Discipline> disciplineList){
        List<Discipline> allDisciplines = refactorDisciplineList(disciplineList);

        Semester semesterToUpdate = getSemesterByIdWithDiscipl(id);

        semesterToUpdate.setName(semester.getName());
        semesterToUpdate.setDuration(semester.getDuration());
        semesterToUpdate.setDisciplineList(allDisciplines);
        this.sessionFactory.getCurrentSession().saveOrUpdate(semesterToUpdate);
    }

    public List<Semester> getSemesterList(){
        return this.sessionFactory.getCurrentSession().createQuery("from Semester s").list();
    }

    public Semester getSemesterById(Integer idSem){
        return (Semester)this.sessionFactory.getCurrentSession().createCriteria(Semester.class)
                .add(Restrictions.idEq(idSem)).uniqueResult();
    }

    public Semester getSemesterByIdWithDiscipl(Integer idSem){
        Semester semester = (Semester)this.sessionFactory.getCurrentSession().createCriteria(Semester.class)
                .add(Restrictions.idEq(idSem)).uniqueResult();
        Hibernate.initialize(semester.getDisciplineList());
        return semester;
    }

    public List<Discipline> getDisciplineList(){
        return this.sessionFactory.getCurrentSession().createCriteria(Discipline.class).list();
    }

    public List<Discipline> getDisciplineListBySemId(Integer semId){
        return this.sessionFactory.getCurrentSession()
                .createQuery("from Discipline d where d.semester.id=" + semId).list();
    }

    public void removeSemester(Integer id){
        Semester semester = getSemesterById(id);
        if(null != semester){
            List<Discipline> disciplineList = getDisciplineListBySemId(id);
            for(Discipline disc : disciplineList){
                if(disc.getSemesterList().size() == 1){
                    List<Progress> progress = this.sessionFactory.getCurrentSession()
                            .createQuery("from Progress p where p.discipline.id=" + disc.getId()).list();
                    for(Progress prog: progress) {
                        this.sessionFactory.getCurrentSession().delete(prog);
                    }
                }
            }

            this.sessionFactory.getCurrentSession().delete(semester);
        }
    }

    public static String selectWordNedeli(int duration){
        if(duration >= 5 && duration <= 20) return "недель";
        else if(duration == 1 || duration % 10 == 1) return "неделя";
        else if(duration % 10 >= 2 && duration % 10 <= 4) return "недели";
        else return "недель";
    }


    public Set<String> getSelectedDisciplineName(int semesterId){
        List<Discipline> disciplineListBySemId = getDisciplineListBySemId(semesterId);
        Set<String> selected = new HashSet<>();

        for(Discipline elem: disciplineListBySemId){
            selected.add(elem.getName());
        }

        return selected;
    }
}

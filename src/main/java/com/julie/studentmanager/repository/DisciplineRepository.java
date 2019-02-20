package com.julie.studentmanager.repository;

import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.domain.Progress;
import com.julie.studentmanager.domain.Semester;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DisciplineRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void setDiscipline(Discipline discipline){
        this.sessionFactory.getCurrentSession().save(discipline);
    }

    public List<Discipline> getDisciplineList(){
        return this.sessionFactory.getCurrentSession().createQuery("from Discipline ")
                .list();
    }

    public Discipline getDisciplineById(Integer id){
        return (Discipline) this.sessionFactory.getCurrentSession().createCriteria(Discipline.class)
                .add(Restrictions.idEq(id)).uniqueResult();
    }

    public void updateDiscipline(Discipline discipline){
        Discipline disciplineToUpdate = getDisciplineById(discipline.getId());
        disciplineToUpdate.setName(discipline.getName());

        this.sessionFactory.getCurrentSession().update(disciplineToUpdate);
    }

    public void removeDiscipline(Integer id){
            Discipline discipline = getDisciplineById(id);

            if(null != discipline) {
                //delete all progress with current discipline
            List<Progress> progress = this.sessionFactory.getCurrentSession()
                    .createQuery("from Progress p where p.discipline.id =" + id).list();
                for (Progress elem : progress) {
                    this.sessionFactory.getCurrentSession().delete(elem);
                }

                List<Semester> semesterList = discipline.getSemesterList();
                this.sessionFactory.getCurrentSession().delete(discipline);
                updateSemesterList(semesterList);
            }
    }

    public void updateSemesterList(List<Semester> semesterList){
           for(Semester elem: semesterList){
               List<Discipline> disciplineList = this.sessionFactory.getCurrentSession()
                       .createQuery("from Discipline d where d.semester.id=" + elem.getId()).list();
               elem.setDisciplineList(disciplineList);
               this.sessionFactory.getCurrentSession().update(elem);
           }
    }

}

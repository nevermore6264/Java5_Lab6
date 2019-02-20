package com.julie.studentmanager.repository;


import com.julie.studentmanager.domain.Discipline;
import com.julie.studentmanager.domain.Progress;
import com.julie.studentmanager.domain.Semester;
import com.julie.studentmanager.domain.Student;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class StudentProgressRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Student getStudentById(Integer id){
        return (Student) this.sessionFactory.getCurrentSession().get(Student.class, id);
    }

    public List<Semester> getSemesterList(){
        return this.sessionFactory.getCurrentSession().createQuery("from Semester s").list();
    }

    public Semester getSemesterById(Integer idSem){
        return (Semester)this.sessionFactory.getCurrentSession().createCriteria(Semester.class)
                .add(Restrictions.idEq(idSem)).uniqueResult();
    }

    public Map<Discipline, Integer> getProgressListByStudIdAndSemId(Integer stuId, Integer semId) {
        String sql = "select p.value, p.discipline from Progress p join p.student s join p.discipline d " +
                "where s.id=" + stuId + " and d.semester.id=" + semId;
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);

        List<Object> selestedObjectList = query.list();

        Map<Discipline, Integer> disciplinePointMap = new HashMap<>();
        Iterator itr = selestedObjectList.iterator();

        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            disciplinePointMap.put((Discipline) obj[1], Integer.parseInt(String.valueOf(obj[0])));
        }

        return disciplinePointMap;
    }

    public List<Student> getStudentList(){
        return this.sessionFactory.getCurrentSession().createQuery("from Student ").list();
    }

    public List<Discipline> getDisciplineList(){
        return this.sessionFactory.getCurrentSession().createQuery("from Discipline ")
                .list();
    }

    public void setStudentProgress(Progress progress){
        this.sessionFactory.getCurrentSession().saveOrUpdate(progress);
    }

    public double getAvaragePointOfStudentProgress(int studId, int semId){
        Object object = this.sessionFactory.getCurrentSession()
                .createQuery("select avg(p.value) from Progress p where p.student.id=" + studId + " and p.discipline.semester.id=" + semId)
                .uniqueResult();

        return Double.parseDouble(String.valueOf(object));
    }

    public List<Discipline> getDisciplineListBySemId(Integer semId){
        return this.sessionFactory.getCurrentSession()
                .createQuery("from Discipline d where d.semester.id=" + semId).list();
    }
}


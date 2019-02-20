package com.julie.studentmanager.repository;

import com.julie.studentmanager.domain.Progress;
import com.julie.studentmanager.domain.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public void setStudent(Student student){
        this.sessionFactory.getCurrentSession().save(student);
    }

    public List<Student> getStudentList(){
        return this.sessionFactory.getCurrentSession().createQuery("from Student ").list();
    }

    public Student getStudentById(Integer id){
        return (Student) this.sessionFactory.getCurrentSession().get(Student.class, id);
    }

    public void updateStudent(Student student){
        Student studentToUpdate = getStudentById(student.getId());

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setSecondName(student.getSecondName());
        studentToUpdate.setGroup(student.getGroup());
        studentToUpdate.setEntranceDate(student.getEntranceDate());

        this.sessionFactory.getCurrentSession().update(studentToUpdate);
    }

    public void removeStudent(Integer id){
        Student student = getStudentById(id);

        if(null != student){
            List<Progress> progress = this.sessionFactory.getCurrentSession()
                    .createQuery("from Progress p where p.student.id =" + id).list();

            for(Progress elem: progress) {
                this.sessionFactory.getCurrentSession().delete(elem);
            }

            this.sessionFactory.getCurrentSession().delete(student);
        }
    }

}

package com.julie.studentmanager.domain;

import javax.persistence.*;

@Entity
@Table(name = "progress")
public class Progress{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    private Integer id;


    @Column(name = "value", nullable = false)
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    private int value;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "students_id", referencedColumnName = "id", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
    @JoinColumn(name = "disciplines_id", referencedColumnName = "id", nullable = false)
    public Discipline getDiscipline(){
        return discipline;
    }
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }
    private Discipline discipline;

}
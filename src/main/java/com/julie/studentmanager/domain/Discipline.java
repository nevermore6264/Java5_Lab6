package com.julie.studentmanager.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "disciplines")
public class Discipline{
    public Discipline() {
    }

    public Discipline(String name) {
        this.name = name;
    }


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



    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "disciplines_has_semester",
    joinColumns = @JoinColumn(name = "disciplines_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "semester_id", referencedColumnName = "id"))
    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    private Semester semester;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "disciplines_has_semester",
    joinColumns = @JoinColumn(name = "disciplines_id"),
    inverseJoinColumns = @JoinColumn(name = "semester_id"))
    public List<Semester> getSemesterList(){
        return this.semesterList;
    }
    public void setSemesterList(List<Semester> semesterList){
        this.semesterList = semesterList;
    }
    private List<Semester> semesterList;

}
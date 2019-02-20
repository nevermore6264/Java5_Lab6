package com.julie.studentmanager.domain;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "semester")
public class Semester{
    public Semester() {
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
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "duration", nullable = false)
    private Integer duration;
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "disciplines_has_semester",
            joinColumns = @JoinColumn(name = "semester_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "disciplines_id", nullable = false))
    @IndexColumn(name = "idx")
    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }
    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
    private List<Discipline> disciplineList;

}

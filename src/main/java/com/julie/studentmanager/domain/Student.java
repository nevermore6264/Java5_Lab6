package com.julie.studentmanager.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="students")
public class Student{

    public Student(){}

    public Student(String firstName, String secondName, String group, Date entranceDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.group = group;
        this.entranceDate = entranceDate;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "groupe", nullable = false)
    private String group;

    @Column(name = "entrance_date", nullable = false)
    private Date entranceDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }
}

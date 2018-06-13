package com.fpoly.entity;

import java.util.Date;
import java.util.Collection;
import java.util.*;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Staffs {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Gender")
    private boolean gender;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "Birthday")
    private Date birthday;

    @Column(name = "Photo")
    private String photo;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Salary")
    private float salary;

    @Column(name = "Notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "DepartId")
    private Departs departs;

    @OneToMany(mappedBy = "staffs", fetch = FetchType.EAGER)
    private Collection<Records> records;

    public Staffs() {
        // TODO Auto-generated constructor stub
    }

    public Staffs(String id, String name, boolean gender, Date birthday, String photo, String email, String phone,
                  int salary, String notes, Departs departs, Collection<Records> records) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.notes = notes;
        this.departs = departs;
        this.records = records;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Departs getDeparts() {
        return departs;
    }

    public void setDeparts(Departs departs) {
        this.departs = departs;
    }

    public Collection<Records> getRecords() {
        return records;
    }

    public void setRecords(Collection<Records> records) {
        this.records = records;
    }

}

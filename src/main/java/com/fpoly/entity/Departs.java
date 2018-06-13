package com.fpoly.entity;

import java.util.Collection;

import javax.persistence.*;

import com.fpoly.entity.*;

@Entity
public class Departs {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "departs", fetch = FetchType.EAGER)
    private Collection<Staffs> staffs;

    public Departs() {
    }

    public Departs(String id, String name, Collection<Staffs> staffs) {
        super();
        this.id = id;
        this.name = name;
        this.staffs = staffs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Collection<Staffs> getStaffs() {
        return staffs;
    }

    public void setStaffs(Collection<Staffs> staffs) {
        staffs = staffs;
    }

}

package com.fpoly.entity;

import javax.persistence.*;

import com.fpoly.entity.Staffs;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Records")
public class Records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "Type")
    private boolean type;


    @Column(name = "Reason")
    private String reason;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "StaffId")
    private Staffs staffs;

    public Records() {
        // TODO Auto-generated constructor stub
    }

    public Records(long id, boolean type, String reason, Date date, Staffs staffs) {
        super();
        this.id = id;
        this.type = type;
        this.reason = reason;
        this.date = date;
        this.staffs = staffs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Staffs getStaffs() {
        return staffs;
    }

    public void setStaffs(Staffs staffs) {
        this.staffs = staffs;
    }

}

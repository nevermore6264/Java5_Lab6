package com.fpoly.controller;

import com.fpoly.entity.Records;
import com.fpoly.entity.Staffs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Controller
@RequestMapping("/record/")
public class RecordController {
    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("record", new Records());
        return "record/add";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model,
                         @ModelAttribute("record") Records record) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            record.setDate(new Date());
            session.persist(record);
            t.commit();
            model.addAttribute("message", "Thêm mới thành công !");
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "Thêm mới thất bại !");
        } finally {
            session.close();
        }
        return "record/add";
    }

    @ModelAttribute("staffs")
    public List<Staffs> getStaffs() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Staffs";
        Query query = session.createQuery(hql);
        List<Staffs> list = query.list();
        return list;
    }
}

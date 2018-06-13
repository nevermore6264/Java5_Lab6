package com.fpoly.controller;

import com.fpoly.entity.Users;
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
import java.util.List;

@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    SessionFactory factory;

    @RequestMapping("index")
    public String index(ModelMap model) {
        Session session = factory.getCurrentSession();
        String hql = "FROM Users";
        Query query = session.createQuery(hql);
        List<Users> list = query.list();
        model.addAttribute("users", list);
        System.out.println(list.size());
        return "user/home";
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insert(ModelMap model) {
        model.addAttribute("user", new Users());
        return "user/form";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model, @ModelAttribute("user") Users user) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.persist(user);
            t.commit();
            model.addAttribute("message", "Thêm mới thành công !");
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "Thêm mới thất bại !");
        } finally {
            session.close();
        }
        return "user/form";
    }
}
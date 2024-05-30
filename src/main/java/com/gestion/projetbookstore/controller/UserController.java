package com.gestion.projetbookstore.controller;

import com.gestion.projetbookstore.entities.User;
import com.gestion.projetbookstore.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    IServiceUser iServiceUser;

    @GetMapping("all")
    public String all(Model model) {
        model.addAttribute("Users" , iServiceUser.findAllUsers());
        return "displayUser";
    }

    @GetMapping("new")
    public String afficheNewForm(Model model) {
        return "addUser";
    }

    @PostMapping("add")
    public String add(User user) {
        iServiceUser.createUser(user);
        return "redirect:/user/all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        User user = iServiceUser.findUserById(id);
        if (user != null) {
            iServiceUser.deleteUser(user);
        }
        return "redirect:/user/all";
    }

    @GetMapping("update/{id}")
    public String afficheUpdateForm(@PathVariable int id, Model model) {
        User user = iServiceUser.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("update")
    public String update(User user) {
        iServiceUser.updateUser(user);
        return "redirect:/user/all";
    }
}

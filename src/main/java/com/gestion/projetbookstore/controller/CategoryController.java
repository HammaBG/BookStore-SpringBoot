package com.gestion.projetbookstore.controller;


import com.gestion.projetbookstore.entities.Category;
import com.gestion.projetbookstore.service.IServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    IServiceCategory iServiceCategory;

    @GetMapping("all")
    public String listCategory(Model model) {
        model.addAttribute("Categories", iServiceCategory.getAllCategories());
        return "display";
    }

    @GetMapping("new")
    public String afficheNewForm(Model model) {
        return "addCat";
    }

    @PostMapping("add")
    public String add(Category category) {
        iServiceCategory.createCategory(category);
        return "redirect:/category/all";
    }


    @GetMapping("delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        Category category = iServiceCategory.getCategoryById(id);
        if (category != null) {
            iServiceCategory.deleteCategory(category);
        }
        return "redirect:/category/all";
    }


    @GetMapping("update/{id}")
    public String afficheUpdateForm(@PathVariable int id, Model model) {
        Category category = iServiceCategory.getCategoryById(id);
        model.addAttribute("category", category);
        return "updateCat";
    }

    @PostMapping("update")
    public String update(Category category) {
        iServiceCategory.updateCategory(category);
        return "redirect:/category/all";

    }

}

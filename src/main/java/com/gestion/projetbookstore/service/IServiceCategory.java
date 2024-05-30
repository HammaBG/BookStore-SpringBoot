package com.gestion.projetbookstore.service;

import com.gestion.projetbookstore.entities.Category;

import java.util.List;

public interface IServiceCategory {
    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Category category);
    public Category getCategoryById(int id);
    public List<Category> getAllCategories();
}

package com.gestion.projetbookstore.repository;

import com.gestion.projetbookstore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

package com.gestion.projetbookstore.repository;

import com.gestion.projetbookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}

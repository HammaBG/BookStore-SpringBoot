package com.gestion.projetbookstore.service;

import com.gestion.projetbookstore.entities.Book;

import java.util.List;

public interface IServiceBook {
    public Book createBook(Book book);
    public Book findBookById(int id);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public List<Book> getAllBooks();
}

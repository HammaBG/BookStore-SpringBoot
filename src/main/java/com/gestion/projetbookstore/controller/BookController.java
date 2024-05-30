package com.gestion.projetbookstore.controller;


import com.gestion.projetbookstore.entities.Book;
import com.gestion.projetbookstore.service.IServiceBook;
import com.gestion.projetbookstore.service.IServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IServiceBook bookService;
    @Autowired
    IServiceCategory iServiceCategory;

    private String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\photos";

    @GetMapping("all")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("Categories", iServiceCategory.getAllCategories());
        return "displayBooks";
    }

    @GetMapping("new")
    public String showAddForm(Model model) {
        model.addAttribute("Categories", iServiceCategory.getAllCategories());
        return "addBook";
    }

    @PostMapping("add")
    public String addBook(@ModelAttribute("book") Book book ,@RequestParam("file") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory , fileName);
        try {
            Files.write(fileNameAndPath, multipartFile.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        book.setPhoto(fileName);
        bookService.createBook(book);
        return "redirect:/books/all";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable int id) {
        Book book = bookService.findBookById(id);
        if (book != null) {
            bookService.deleteBook(book);
        }
        return "redirect:/books/all";
    }

    @GetMapping("update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Book book = bookService.findBookById(id);

        model.addAttribute("categories", iServiceCategory.getAllCategories());
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("update")
    public String updateBook(@ModelAttribute("book") Book book, @RequestParam("file") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
            try {
                Files.write(fileNameAndPath, multipartFile.getBytes());
                book.setPhoto(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bookService.updateBook(book);
        return "redirect:/books/all";
    }


}

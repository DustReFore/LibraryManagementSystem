package com.example.first_project.controller;

import com.example.first_project.model.Book;
import com.example.first_project.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private List<Book> books =  new ArrayList<>();

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book_form";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        book.setId(books.size() + 1);
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        model.addAttribute("book", book);
        return "book_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        books.removeIf(b -> b.getId() == id);
        return "redirect:/books";
    }
}

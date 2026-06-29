package com.example.first_project.controller;

import com.example.first_project.model.Author;
import com.example.first_project.model.Book;
import com.example.first_project.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final LibraryService libraryService;

    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", libraryService.getBooks());
        return "books";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", libraryService.getAuthors());
        model.addAttribute("formAction", "/books/add");
        model.addAttribute("formTitle", "Добавить книгу");
        model.addAttribute("submitText", "Сохранить книгу");
        return "book_form";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        // найти автора по id
        Author author = libraryService.getAuthors()
                .stream()
                .filter(a -> a.getId() == book.getAuthor().getId())
                .findFirst()
                .orElse(null);

        book.setAuthor(author);
        book.setId(libraryService.getBooks().size() + 1);
        libraryService.addBook(book);
        return "redirect:/books";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Book book = libraryService.getBooks().stream().filter(b -> b.getId() == id).findFirst().orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("authors", libraryService.getAuthors());
        model.addAttribute("formAction", "/books/edit/" + id);
        model.addAttribute("formTitle", "Редактировать книгу");
        model.addAttribute("submitText", "Сохранить изменения");
        return "book_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        libraryService.getBooks().removeIf(b -> b.getId() == id);
        return "redirect:/books";
    }
}

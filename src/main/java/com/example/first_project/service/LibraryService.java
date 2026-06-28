package com.example.first_project.service;

import com.example.first_project.model.Author;
import com.example.first_project.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();

    public List<Book> getBooks() {return books;}
    public List<Author> getAuthors() {return authors;}

    public void addBook(Book book) {
        books.add(book);
        if (book.getAuthor() != null) {
            book.getAuthor().getBooks().add(book);
        }
    }
    public void addAuthor(Author author) {authors.add(author);}
}

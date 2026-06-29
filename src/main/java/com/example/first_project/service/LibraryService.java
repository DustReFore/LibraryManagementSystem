package com.example.first_project.service;

import com.example.first_project.model.Author;
import com.example.first_project.model.Book;
import com.example.first_project.model.Reader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();

    public List<Book> getBooks() {return books;}
    public List<Author> getAuthors() {return authors;}
    public List<Reader> getReaders() {return readers;}

    public void addBook(Book book) {
        books.add(book);
        if (book.getAuthor() != null) {
            book.getAuthor().getBooks().add(book);
        }
    }
    public void addAuthor(Author author) {authors.add(author);}
    public void addReader(Reader reader) {readers.add(reader);}
}

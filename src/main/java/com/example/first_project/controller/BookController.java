package com.example.first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    @RequestMapping("/books")
    public String books() {
        return "books";
    }
}

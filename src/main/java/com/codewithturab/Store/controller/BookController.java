package com.codewithturab.Store.controller;

import com.codewithturab.Store.model.Book;
import com.codewithturab.Store.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BookController {

    private final BookService service;

    BookController(BookService service) {
        this.service = service;
    }

    public List<Book> getBooks() {
        return service.getAllBooks();
    }
}

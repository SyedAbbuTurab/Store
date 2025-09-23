package com.codewithturab.Store.controller;

import com.codewithturab.Store.model.Book;
import com.codewithturab.Store.service.BookService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.codewithturab.Store.dto.BookResponse;
import com.codewithturab.Store.dto.BookRequest;

import java.util.List;
import java.util.stream.Collectors;
// Till here

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookResponse> getBooks() {
        return service.getAllBooks().stream()
                .map(book -> new BookResponse(book.getId(),  book.getTitle(), book.getAuthor()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }
}

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

    @GetMapping("{/id}")
    public BookResponse getBookById(@PathVariable String id) {
        Book book = service.getBookById(id)
        .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor());
    }

    @PostMapping
    public BookResponse createBook(@RequestBody @Valid BookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor());
        Book saved = service.createBook((book));
        return new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor());
    }

    public  BookResponse updateBook(@PathVariable String id, @Valid @RequestBody BookRequest request) {
        Book updatedBook = new Book(request.getTitle(), request.getAuthor());
        Book saved = service.updateBook(id, updatedBook);
        return  new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor());
    }

    @DeleteMapping("{/id}")
    public void deleteBook(@PathVariable String id) {
        service.deleteBook(id);
    }
}

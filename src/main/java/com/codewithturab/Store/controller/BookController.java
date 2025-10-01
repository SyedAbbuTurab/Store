package com.codewithturab.Store.controller;

import com.codewithturab.Store.model.Book;
import com.codewithturab.Store.service.BookService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.codewithturab.Store.security.JwtUtil;
import com.codewithturab.Store.dto.BookResponse;
import com.codewithturab.Store.dto.BookRequest;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    private final BookService service;
    private final JwtUtil jwtUtil;

    BookController(BookService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<BookResponse> getBooks(@RequestHeader("Authorization") String authHeader) {
        logger.info("📚 Fetching all books");
        String token = authHeader.replace("Bearer ", "");

        if(!jwtUtil.isTokenValid(token)) {
            throw new RuntimeException("Invalid or expired token");
        }
        List<BookResponse> books = service.getAllBooks().stream()
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor()))
                .toList();
        logger.debug("Books retrieved: {}", books.size());
        return books;
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    public  BookResponse updateBook(@PathVariable String id, @Valid @RequestBody BookRequest request) {
        Book updatedBook = new Book(request.getTitle(), request.getAuthor());
        Book saved = service.updateBook(id, updatedBook);
        return  new BookResponse(saved.getId(), saved.getTitle(), saved.getAuthor());
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        service.deleteBook(id);
    }

    @GetMapping("/admin")
    public String adminOnly(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        if(!jwtUtil.isTokenValid(token)) {
            throw  new RuntimeException("Invalid or Expired toke!");
        }

        String role = jwtUtil.extractRole(token);

        if(!"ADMIN".equals(role)) {
            throw new RuntimeException("Access denied. Admins only.");
        }

        return "Welcome Admin";
    }
}

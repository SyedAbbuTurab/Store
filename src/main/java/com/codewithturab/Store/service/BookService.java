package com.codewithturab.Store.service;

import com.codewithturab.Store.model.Book;
import com.codewithturab.Store.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book createBook(Book book) {
        return repo.save(book);
    }

    public void deleteBook(String id) {
        repo.deleteById(id);
    }

}

package com.codewithturab.Store.service;

import com.codewithturab.Store.model.Book;
import com.codewithturab.Store.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Book> getBookById(String id) {
        return  repo.findById(id);
    }

    public Book updateBook(String id, Book updatedBook) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedBook.getTitle());
                    existing.setAuthor(updatedBook.getAuthor());
                return repo.save(existing);
                }).orElseThrow(() -> new RuntimeException("Book not found with id:" + id));
    }

    public void deleteBook(String id) {
        repo.deleteById(id);
    }
}

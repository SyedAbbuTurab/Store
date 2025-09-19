package com.codewithturab.Store.repository;

import com.codewithturab.Store.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}

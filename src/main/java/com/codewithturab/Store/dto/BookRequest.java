package com.codewithturab.Store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class BookRequest {
    @NotBlank(message = "Title cannot be empty")
    @Size(min=2, max = 100, message = "Title must be between 2 to 100 characters")
    private String title;

    @NotBlank(message = "Author name cannot be empty")
    @Size(min=3, max=20, message = "Author name must be between 3 to 20 characters")
    private String author;

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

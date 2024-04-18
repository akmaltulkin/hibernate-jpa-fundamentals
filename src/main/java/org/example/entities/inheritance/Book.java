package org.example.entities.inheritance;

import jakarta.persistence.Entity;

@Entity
public class Book extends Products {

    private String author;

    public Book() {
    }

    public Book(String name, String author) {
        super(name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

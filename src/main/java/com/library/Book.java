package com.library;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Book {

    @Getter
    private String title;
    @Getter
    private String author;
    private String yearPublished;
    private String uniqueISBN;
    @Setter
    @Getter
    private String status;
    public Book(String title, String author, String yearPublished, String uniqueISBN, String status) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.uniqueISBN = uniqueISBN;
        this.status = status;
    }
    public String toString() {
        return String.format("Title: " +title+ " | Author: " +author+ " | Year published: " +yearPublished+ " | ISBN: "+uniqueISBN + "| Availabilty: "+status);
    }
}

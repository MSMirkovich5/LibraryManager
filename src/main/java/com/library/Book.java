package com.library;

import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private int yearPublished;
    private String ISBN;
    public Book(String title, String author, int yearPublished, String ISBN) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.ISBN = ISBN;
    }

}

package com.library;

import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private String yearPublished;
    private String uniqueISBN;
    public Book(String title, String author, String yearPublished, String uniqueISBN) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.uniqueISBN = uniqueISBN;
    }

}

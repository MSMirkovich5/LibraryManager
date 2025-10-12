package com.library;

import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private String yearPublished;
    private String uniqueISBN;
    private String status;
    public Book(String title, String author, String yearPublished, String uniqueISBN, String status) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.uniqueISBN = uniqueISBN;
        this.status = status;
    }
    @Override
    public String toString() {
        return String.format("Title: " +title+ " | Author: " +author+ " | Year published: " +yearPublished+ " | ISBN: "+uniqueISBN + "| Availabilty: "+status);
    }
    public String getTitle() {
        return String.format(title);
    }
    public String getAuthor() {
        return String.format(author);
    }
    public String getStatus() {
        return String.format(status);
    }
    public void setStatus(String s) {
        status = s;
    }
}

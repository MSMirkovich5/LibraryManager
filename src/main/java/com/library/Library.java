package com.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Library {

    ArrayList<Book> enitreLibrary = new ArrayList<Book>();

    public void addBook(Book b) {
        enitreLibrary.add(b);
    }
    public Book searchBook(String choiceTitleOrAuthor, String bookTitleOrAuthor) {
        if (choiceTitleOrAuthor.equalsIgnoreCase("title")) {
            for (Book book : enitreLibrary) {
                if (book.getTitle().equalsIgnoreCase(bookTitleOrAuthor)) {
                    System.out.println("We have that book in our library!");
                    return book;
                }
                else {
                    System.out.println("Unfortunately, we do not have that book in our library.");
                    return null;
                }
            }
        }
        if (choiceTitleOrAuthor.equalsIgnoreCase("author")) {
            for (Book book : enitreLibrary) {
                if (book.getAuthor().equalsIgnoreCase(bookTitleOrAuthor)) {
                    System.out.println("We have that book in our library!");
                    return book;
                }
                else{
                    System.out.println("Unfortunately, we do not have any of that author's books in our library.");
                    return null;
                }
            }
        }
        return null;
    }
    public void borrowBook(Book foundBook, String yesOrNo) {
        if (foundBook == null) {
            return;
        }
        if (foundBook.getStatus().equalsIgnoreCase("Borrowed")) {
            System.out.println("This book has already been borrowed!");
            return;
        }
        if (yesOrNo.equalsIgnoreCase("yes")) {
            foundBook.setStatus("Borrowed");
            System.out.println("You borrowed "+foundBook.getTitle()+" from the library!");
        }
        else {
            System.out.println("Book remains with us.");
        }
    }
    public void returnBook(Book foundBook, String yesOrNo) {
        if (foundBook == null) {
            return;
        }
        if (foundBook.getStatus().equalsIgnoreCase("Available")) {
            System.out.println("This book is already in our library!");
            return;
        }
        if (foundBook.getStatus().equalsIgnoreCase("Borrowed")) {
            if (yesOrNo.equalsIgnoreCase("yes")) {
                foundBook.setStatus("Available");
                System.out.println("You returned "+foundBook.getTitle()+" to the library!");
            }
            else {
                System.out.println("Book remains with you.");
            }
        }
        else {
            System.out.println("Book is in our library!");
        }
    }
    public void printOutEnitreLibrary() {
        if  (enitreLibrary.isEmpty()) {
            System.out.println("There are currently no books in our library!");
        }
        else{
            System.out.println("The library contains the following books: ");
            for (Book book : enitreLibrary) {
                System.out.println(book);
            }
        }
    }
}

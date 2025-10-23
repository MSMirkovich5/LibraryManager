package com.library;

import java.util.*;

import static com.library.Main.AUTHOR;
import static com.library.Main.TITLE;

public class Library {

    Set<Book> enitreLibrary = new HashSet<>();

    public void addBook(Book book) {
        enitreLibrary.add(book);
    }
    public Optional<Book> searchBook(String choiceTitleOrAuthor, String bookTitleOrAuthor) {
        if (choiceTitleOrAuthor.equalsIgnoreCase(TITLE)) {
            for (Book book : enitreLibrary) {
                if (book.getTitle().equalsIgnoreCase(bookTitleOrAuthor)) {
                    System.out.println("We have that book in our library!");
                    return Optional.of(book);
                }
            }
            System.out.println("Unfortunately, we do not have that book in our library.");
            return Optional.empty();
        }
        if (choiceTitleOrAuthor.equalsIgnoreCase(AUTHOR)) {
            for (Book book : enitreLibrary) {
                if (book.getAuthor().equalsIgnoreCase(bookTitleOrAuthor)) {
                    System.out.println("We have that book in our library!");
                    return Optional.of(book);
                }
            }
            System.out.println("Unfortunately, we do not have any of that author's books in our library.");
            return Optional.empty();
        }
        return Optional.empty();
    }
    public void borrowBook(Optional<Book> foundBook, String yesOrNo) {
        if (foundBook.isEmpty()) {
            System.out.println("Book not found!");
            return;
        }
        Book book = foundBook.get();
        if (book.getStatus().equalsIgnoreCase("Borrowed")) {
            System.out.println("This book has already been borrowed!");
            return;
        }
        if (yesOrNo.equalsIgnoreCase("yes")) {
            book.setStatus("Borrowed");
            System.out.println("You borrowed "+book.getTitle()+" from the library!");
        }
        else {
            System.out.println("Book remains with us.");
        }
    }
    public void returnBook(Optional<Book> foundBook, String yesOrNo) {
        if (foundBook.isEmpty()) {
            System.out.println("Book not found!");
            return;
        }
        Book book = foundBook.get();
        if (book.getStatus().equalsIgnoreCase("Available")) {
            System.out.println("This book is already in our library!");
            return;
        }
        if (book.getStatus().equalsIgnoreCase("Borrowed")) {
            if (yesOrNo.equalsIgnoreCase("yes")) {
                book.setStatus("Available");
                System.out.println("You returned "+book.getTitle()+" to the library!");
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

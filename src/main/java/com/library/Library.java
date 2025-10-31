package com.library;

import java.sql.*;
import java.util.*;

public class Library {

    Set<Book> entireLibrary = new HashSet<>();
    LibraryRepository repository;

    public Library() {
        repository = new LibraryRepository();
        entireLibrary.addAll(repository.addBooksFromDatabase());
    }

    public void addBook(Book book) {
        if (entireLibrary.contains(book)) {
            System.out.println("Book already exists");
            return;
        }
        repository.saveToDatabase(book);
        entireLibrary.add(book);
        System.out.println("Book added successfully!");
    }
    public Optional<Book> searchBook(Main.Constant choiceTitleOrAuthor, String bookTitleOrAuthor) {
        if (searchLibraryUsingTitleOrAuthor(choiceTitleOrAuthor, bookTitleOrAuthor)){
            System.out.println("We have that book in our library!");
            return Optional.of(entireLibrary.iterator().next());
        }
        else{
            System.out.println("Book not found!");
            return Optional.empty();
        }
    }
    public void bookBorrowOrReturn(Optional<Book> foundBook, String yesOrNo, Main.Constant borrowReturn) {
        if (foundBook.isEmpty()) {
            return;
        }
        Book book = foundBook.get();
        if (borrowReturn.equals(Main.Constant.BORROW)) {
            if (book.getStatus().equalsIgnoreCase("Borrowed")) {
                System.out.println("This book has already been borrowed!");
                return;
            }
            if (yesOrNo.equalsIgnoreCase("yes")) {
                book.setStatus("Borrowed");
                repository.updateBookDatabaseStatus(book);
                System.out.println("You borrowed "+book.getTitle()+" from the library!");
            }
            else {
                System.out.println("Book remains with us.");
            }
        }
        if (borrowReturn.equals(Main.Constant.RETURN)) {
            if (book.getStatus().equalsIgnoreCase("Available")) {
                System.out.println("This book is already in our library!");
                return;
            }
            if (yesOrNo.equalsIgnoreCase("yes")) {
                book.setStatus("Available");
                repository.updateBookDatabaseStatus(book);
                System.out.println("You returned "+book.getTitle()+" to the library!");
            }
            else {
                System.out.println("Book remains with you.");
            }
        }
    }
    public void printOutEnitreLibrary() {
        if  (entireLibrary.isEmpty()) {
            System.out.println("There are currently no books in our library!");
        }
        else{
            System.out.println("The library contains the following books: ");
            for (Book book : entireLibrary) {
                System.out.println(book);
            }
        }
    }



    private boolean searchLibraryUsingTitleOrAuthor(Main.Constant titleOrAuthor, String bookTitleOrAuthor) {
        if (titleOrAuthor.equals(Main.Constant.TITLE)) {
            for (Book book : entireLibrary) {
                if (book.getTitle().equalsIgnoreCase(bookTitleOrAuthor)) {
                    return true;
                }
            }
            return false;
        }
        else if (titleOrAuthor.equals(Main.Constant.AUTHOR)) {
            for (Book book : entireLibrary) {
                if (book.getAuthor().equalsIgnoreCase(bookTitleOrAuthor)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}

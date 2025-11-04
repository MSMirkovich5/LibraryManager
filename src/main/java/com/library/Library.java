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
        }
        else {
            repository.saveToDatabase(book);
            entireLibrary.add(book);
            System.out.println("Book added successfully!");
        }
    }
    public Optional<Book> searchBook(Main.Constant choiceTitleOrAuthor, String bookTitleOrAuthor) {
        Optional<Book> foundBook = searchLibraryUsingTitleOrAuthor(choiceTitleOrAuthor, bookTitleOrAuthor);
        if (foundBook.isPresent()) {
            System.out.println("We have " +foundBook.get().getTitle()+ " in our library!");
            return foundBook;
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



    private Optional<Book> searchLibraryUsingTitleOrAuthor(Main.Constant titleOrAuthor, String bookTitleOrAuthor) {
        for (Book book : entireLibrary) {
            if (titleOrAuthor.equals(Main.Constant.TITLE) && book.getTitle().equalsIgnoreCase(bookTitleOrAuthor)) {
                return Optional.of(book);
            }
            if (titleOrAuthor.equals(Main.Constant.AUTHOR) &&  book.getAuthor().equalsIgnoreCase(bookTitleOrAuthor)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }
}

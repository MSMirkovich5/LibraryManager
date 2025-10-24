package com.library;

import java.util.*;

import static com.library.Main.AUTHOR;
import static com.library.Main.TITLE;

public class Library {

    Set<Book> entireLibrary = new HashSet<>();

    public void addBook(Book book) {
        if (checkForDuplicates(book)) return;
        System.out.println("Book added successfully!");
        entireLibrary.add(book);
    }
    public Optional<Book> searchBook(String choiceTitleOrAuthor, String bookTitleOrAuthor) {
        if (searchLibraryUsingTitleOrAuthor(choiceTitleOrAuthor, bookTitleOrAuthor)){
            System.out.println("We have that book in our library!");
            return Optional.of(entireLibrary.iterator().next());
        }
        else{
            System.out.println("Book not found!");
            return Optional.empty();
        }
    }
    public void bookBorrowOrReturn(Optional<Book> foundBook, String yesOrNo, String borrowReturn) {
        if (foundBook.isEmpty()) {
            return;
        }
        Book book = foundBook.get();
        if (borrowReturn.equals("borrow")) {
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
        if (borrowReturn.equals("return")) {
            if (book.getStatus().equalsIgnoreCase("Available")) {
                System.out.println("This book is already in our library!");
                return;
            }
            if (yesOrNo.equalsIgnoreCase("yes")) {
                book.setStatus("Available");
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



    private boolean checkForDuplicates(Book book) {
        for (Book booksInside : entireLibrary) {
            if (booksInside.getTitle().equals(book.getTitle()) && booksInside.getAuthor().equals(book.getAuthor())) {
                System.out.println("Book already exists");
                return true;
            }
        }
        return false;
    }
    private boolean searchLibraryUsingTitleOrAuthor(String titleOrAuthor, String bookTitleOrAuthor) {
        if (titleOrAuthor.equalsIgnoreCase(TITLE)) {
            for (Book book : entireLibrary) {
                if (book.getTitle().equalsIgnoreCase(bookTitleOrAuthor)) {
                    return true;
                }
            }
            return false;
        }
        else if (titleOrAuthor.equalsIgnoreCase(AUTHOR)) {
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

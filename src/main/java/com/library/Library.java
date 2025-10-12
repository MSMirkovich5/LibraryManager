package com.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Library {

    ArrayList<Book> enitreLibrary = new ArrayList<Book>();

    public void addBook(Scanner s) {
        System.out.print("Enter book title: ");
        String bookName = s.nextLine();
        System.out.print("Enter book author: ");
        String bookAuthor = s.nextLine();
        System.out.print("Enter book ISBN: ");
        String bookISBN = s.nextLine();
        System.out.print("Enter the book's publication year: ");
        String bookYear = s.nextLine();
        String bookStatus="Available";
        Book addedBook = new Book(bookName, bookAuthor, bookYear, bookISBN, bookStatus);
        enitreLibrary.add(addedBook);
        System.out.println("Book added successfully!");
    }
    public Book searchBook(Scanner s) {
        if (searchByAuthorOrTitle(s).equalsIgnoreCase("title")) {
            System.out.print("Enter book title: ");
            String bookTitle = s.nextLine();
            for (Book book : enitreLibrary) {
                if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                    System.out.println("We have that book in our library!");
                    return book;
                }
                else {
                    System.out.println("Unfortunately, we do not have that book in our library.");
                    return null;
                }
            }
        }
        if (searchByAuthorOrTitle(s).equalsIgnoreCase("author")) {
            System.out.print("Enter book author: ");
            String bookAuthor = s.nextLine();
            for (Book book : enitreLibrary) {
                if (book.getAuthor().equalsIgnoreCase(bookAuthor)) {
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
    public void borrowBook(Scanner s) {
        Book findBook = searchBook(s);
        if (findBook == null) {
            System.out.println("Unfortunately, we don't have that book in our library!");
        }
        if (findBook.getStatus().equalsIgnoreCase("Borrowed")) {
            System.out.println("This book has already been borrowed!");
        }
        System.out.print("Would you like to borrow the book? (yes/no) ");
        String bookBorrow = s.nextLine();
        if (bookBorrow.equalsIgnoreCase("yes")) {
            findBook.setStatus("Borrowed");
            System.out.println("You borrowed "+findBook.getTitle()+" from the library!");
        }
        else {
            System.out.println("Book remains with us.");
        }
    }
    public void returnBook(Scanner s) {
        Book findBook = searchBook(s);
        if (findBook == null) {
            System.out.println("Unfortunately, we don't have that book in our library!");
            // zbog nekog razloga proba nastavit s metodom umjesto da se vrati u Main i samo pita za continue.
        }
        else if (findBook.getStatus().equalsIgnoreCase("Borrowed")) {
            System.out.print(findBook.getTitle()+" has been borrowed! Would you like to return it? (yes/no) ");
            String bookReturn = s.nextLine();
            if (bookReturn.equalsIgnoreCase("yes")) {
                findBook.setStatus("Available");
                System.out.println("You returned "+findBook.getTitle()+" to the library!");
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
        System.out.println("The library contains the following books: ");
        for (Book book : enitreLibrary) {
            System.out.println(book);
        }
    }
    private static String searchByAuthorOrTitle(Scanner s) {
        String options = "title author";
        System.out.print("Would you like to search by title or by author? ");
        String bookSearch = s.nextLine();
        while(true) {
            if (bookSearch.equalsIgnoreCase("title") || bookSearch.equalsIgnoreCase("author")){
                break;
            }
            System.out.print("Enter either title or author: ");
            bookSearch = s.nextLine();
        }
        return bookSearch;
    }
}

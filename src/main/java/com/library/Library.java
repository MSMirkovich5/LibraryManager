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
        Book addedBook = new Book(bookName, bookAuthor, bookYear, bookISBN);
        enitreLibrary.add(addedBook);
    }
    public void printOutEnitreLibrary() {
        for (Book book : enitreLibrary) {
            System.out.println(book);
        }
    }
}

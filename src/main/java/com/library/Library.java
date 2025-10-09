package com.library;

import java.util.Scanner;

public class Library {
    // Scanner sc = new Scanner(System.in);

    public Book addBook(Scanner s){
        System.out.print("Enter book title: ");
        String bookName = s.nextLine();
        System.out.print("Enter book author: ");
        String bookAuthor = s.nextLine();
        System.out.print("Enter the book's publication year: ");
        int bookYear = s.nextInt();
        System.out.print("Enter book ISBN: ");
        String bookISBN = s.nextLine();
        Book addedBook = new Book(bookName, bookAuthor, bookYear, bookISBN);
        return addedBook;
    }
}

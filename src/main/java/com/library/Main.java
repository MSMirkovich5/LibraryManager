package com.library;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static final String AUTHOR = "author";
    public static final String TITLE = "title";
    public static final String BORROW = "borrow";
    public static final String RETURN = "return";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();

        do {
            String optionSelect = getOptionFromUser(input);
            switch (optionSelect.toLowerCase()) {
                case "add":
                    library.addBook(addBookMain(input));
                    break;
                case "search":
                    String chooseChoice = chooseAuthorOrTitle(input);
                    String searchByChoice = searchBookTitleOrAuthor(input, chooseChoice);
                    library.searchBook(chooseChoice, searchByChoice);
                    break;
                case "borrow":
                    chooseChoice = chooseAuthorOrTitle(input);
                    searchByChoice = searchBookTitleOrAuthor(input, chooseChoice);
                    Optional<Book> foundBook= library.searchBook(chooseChoice, searchByChoice);
                    library.bookBorrowOrReturn(foundBook, borrowOrReturnBook(foundBook, input, BORROW), BORROW);
                    break;
                case "return":
                    chooseChoice = chooseAuthorOrTitle(input);
                    searchByChoice = searchBookTitleOrAuthor(input, chooseChoice);
                    foundBook= library.searchBook(chooseChoice, searchByChoice);
                    library.bookBorrowOrReturn(foundBook, borrowOrReturnBook(foundBook, input, RETURN), RETURN);
                    break;
                case "printout":
                    library.printOutEnitreLibrary();
                    break;
            }
            System.out.print("Would you like to continue? (yes/no) ");
        } while (!input.nextLine().equalsIgnoreCase("no"));
    }



    private static String getOptionFromUser(Scanner input) {
        String options = "Add Search Borrow Return Printout add search borrow return printout";
        System.out.print("Choose an option (Add, Search, Borrow, Return, Printout): ");
        String option = input.nextLine();
        while (!options.contains(option)) {
            System.out.print("Invalid option! Please choose one of the following (Add, Search, Borrow, Return, Printout): ");
            option=input.nextLine();
        }
        return option;
    }
    private static Book addBookMain(Scanner input) {
        System.out.print("Enter book title: ");
        String bookName = input.nextLine();
        System.out.print("Enter book author: ");
        String bookAuthor = input.nextLine();
        System.out.print("Enter book ISBN: ");
        String bookISBN = input.nextLine();
        System.out.print("Enter the book's publication year: ");
        String bookYear = input.nextLine();
        String bookStatus="Available";
        Book addedBook = new Book(bookName, bookAuthor, bookYear, bookISBN, bookStatus);
        return addedBook;
    }
    private static String chooseAuthorOrTitle(Scanner input) {
        while (true) {
            System.out.print("Would you like to search by title or by author? ");
            String bookSearch = input.nextLine();
            if (bookSearch.equalsIgnoreCase(TITLE) || bookSearch.equalsIgnoreCase(AUTHOR)) {
                return bookSearch;
            }
            System.out.print("Enter either title or author: ");
        }
    }
    private static String searchBookTitleOrAuthor(Scanner input, String authorOrTitle) {
        System.out.print("Enter book "+authorOrTitle+": ");
        return input.nextLine();
    }
    private static String borrowOrReturnBook(Optional<Book> book,Scanner input, String borrowReturn) {
        if (book.isEmpty()) {
            return null;
        }
        if (borrowReturn.equalsIgnoreCase(BORROW)) {
            System.out.print("Would you like to borrow the book? (yes/no) ");
        }
        else {
            System.out.print("Would you like to return the book? (yes/no) ");
        }
        return input.nextLine();
    }
}

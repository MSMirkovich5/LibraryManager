package com.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library libra = new Library();

        while (true) {
            String optionSelect = getOptionFromUser(input);
            switch(optionSelect.toLowerCase()) {
                case "add":
                    libra.addBook(addBookMain(input));
                    break;
                case "search":
                    String chooseChoice = chooseAuthorOrTitle(input);
                    String searchByChoice = searchBookTitleOrAuthor(input, chooseChoice);
                    libra.searchBook(chooseChoice,searchByChoice);
                    break;
                case "borrow":
                    chooseChoice = chooseAuthorOrTitle(input);
                    searchByChoice = searchBookTitleOrAuthor(input, chooseChoice);
                    libra.borrowBook(libra.searchBook(chooseChoice, searchByChoice), borrowOrReturnBook(input,"borrow"));
                    break;
                case "return":
                    chooseChoice = chooseAuthorOrTitle(input);
                    searchByChoice = searchBookTitleOrAuthor(input, chooseChoice);
                    libra.returnBook(libra.searchBook(chooseChoice, searchByChoice), borrowOrReturnBook(input,"return"));
                    break;
                case "printout":
                    libra.printOutEnitreLibrary();
                    break;
            }
            System.out.print("Would you like to continue? (yes/no) ");
            if (input.nextLine().equalsIgnoreCase("no")) break;
        }
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
        System.out.println("Book added successfully!");
        return addedBook;
    }
    private static String chooseAuthorOrTitle(Scanner input) {
        while (true) {
            System.out.print("Would you like to search by title or by author? ");
            String bookSearch = input.nextLine();
            if (bookSearch.equalsIgnoreCase("title") || bookSearch.equalsIgnoreCase("author")) {
                return bookSearch;
            }
            System.out.print("Enter either title or author: ");
        }
    }
    private static String searchBookTitleOrAuthor(Scanner input, String authorOrTitle) {
        System.out.print("Enter book "+authorOrTitle+": ");
        return input.nextLine();
    }
    private static String borrowOrReturnBook(Scanner input, String borrowReturn) {
        if (borrowReturn.equalsIgnoreCase("Borrow")) {
            System.out.print("Would you like to borrow the book? (yes/no) ");
        }
        else {
            System.out.print("Would you like to return the book? (yes/no) ");
        }
        return input.nextLine();
    }
}

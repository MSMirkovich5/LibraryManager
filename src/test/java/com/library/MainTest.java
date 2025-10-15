package com.library;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testOptionSelect(){
        Scanner sc = new Scanner("add");
        String option = copyGetOptionFromUser(sc);
        assertEquals("add", option);
    }
    @Test
    void testOptionSelectInvalidThenValidInput(){
        Scanner sc = new Scanner("pizza \nsearch");
        String option = copyGetOptionFromUser(sc);
        assertEquals("search", option);
    }
    @Test
    void testChooseAuthorOrTitle(){
        Scanner sc = new Scanner("title");
        String option = copyChooseAuthorOrTitle(sc);
        assertEquals("title", option);
    }
    @Test
    void testChooseAuthorOrTitleInvalidThenValidInput(){
        Scanner sc = new Scanner("pasta \ntitle");
        String option = copyChooseAuthorOrTitle(sc);
        assertEquals("title", option);
    }

    private static String copyGetOptionFromUser(Scanner input) {
        String options = "Add Search Borrow Return Printout add search borrow return printout";
        System.out.print("Choose an option (Add, Search, Borrow, Return, Printout): ");
        String option = input.nextLine();
        while(!options.contains(option)) {
            System.out.println("Invalid option! Please choose one of the following (Add, Search, Borrow, Return, Printout): ");
            option = input.nextLine();
        }
        return option;
    }
    private static String copyChooseAuthorOrTitle(Scanner input) {
        while (true) {
            System.out.print("Would you like to search by title or by author? ");
            String bookSearch = input.nextLine();
            if (bookSearch.equalsIgnoreCase("title") || bookSearch.equalsIgnoreCase("author")) {
                return bookSearch;
            }
            System.out.print("Enter either title or author: ");
        }
    }
}
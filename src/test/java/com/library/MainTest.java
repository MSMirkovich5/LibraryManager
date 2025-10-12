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

    private static String copyGetOptionFromUser(Scanner input) {
        String options = "add search borrow return printout";
        System.out.print("Choose an option (Add, Search, Borrow, Return, Printout): ");
        String option = input.nextLine();
        while(!options.contains(option)) {
            System.out.println("Invalid option! Please choose one of the following (Add, Search, Borrow, Return, Printout): ");
            option = input.nextLine();
        }
        return option;
    }
}
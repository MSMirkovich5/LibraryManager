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
                    libra.addBook(input);
                case "printout":
                    libra.printOutEnitreLibrary();
            }
            System.out.print("Would you like to continue? (yes/no) ");
            if (input.nextLine().equalsIgnoreCase("no")) break;
        }
    }



    private static String getOptionFromUser(Scanner input) {
        System.out.print("Choose an option (Add, Search, Borrow, Return, Printout): ");
        String option = input.nextLine();
        return option;
    }
}

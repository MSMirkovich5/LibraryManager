package com.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String optionSelect = getOptionFromUser(input);

    }



    private static String getOptionFromUser(Scanner input) {
        System.out.println("Choose an option (Add, Search, Borrow, Return, Printout): ");
        String option = input.nextLine();
        return option;
    }
}

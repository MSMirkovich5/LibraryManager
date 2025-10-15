package com.library;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testSearchBook(){
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals(book, library.searchBook("title","Moby Dick"));
    }
    @Test
    void testSearchBookNotInLibrary() {
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals(null, library.searchBook("title","Jony"));
    }aa
}
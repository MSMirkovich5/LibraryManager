package com.library;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void testSearchBook(){
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals(Optional.of(book), library.searchBook(BookConstants.TITLE,"Moby Dick"));
    }
    @Test
    void testSearchBookNotInLibrary() {
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals(Optional.empty(), library.searchBook(BookConstants.TITLE,"Jony"));
    }
    @Test
    void testAddingDuplicateBook() {
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        Book book2 = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals("Book already exists",library.addBook(book2));
    }
}
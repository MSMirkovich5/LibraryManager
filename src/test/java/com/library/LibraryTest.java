package com.library;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    Set<Book> entireLibrary = new HashSet<>();

    @Test
    void testSearchBook(){
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals(Optional.of(book), library.searchBook("title","Moby Dick"));
    }
    @Test
    void testSearchBookNotInLibrary() {
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        library.addBook(book);
        assertEquals(Optional.empty(), library.searchBook("title","Jony"));
    }
    @Test
    void testAddingDuplicateBook() {
        Library library = new Library();
        Book book = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        Book book2 = new Book("Moby Dick","Herman Melville","1851","123456","Available");
        entireLibrary.add(book);
        assertEquals(true,copyCheckForDuplicates(book2));
    }

    private boolean copyCheckForDuplicates(Book book) {
        for (Book booksInside : entireLibrary) {
            if (booksInside.getTitle().equals(book.getTitle()) && booksInside.getAuthor().equals(book.getAuthor())) {
                System.out.println("Book already exists");
                return true;
            }
        }
        return false;
    }
}
package com.library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@Transactional
public class LibraryService {
    private final BookRepository repository;
    private final Set<Book> entireLibrary = new HashSet<>();
    @Autowired
    public LibraryService(BookRepository repository) {
        this.repository = repository;
        repository.findAll().forEach(entireLibrary::add);
    }
    public String addBook(Book book) {
        Optional<Book> existing = repository.findByTitleIgnoreCaseAndAuthorIgnoreCase(book.getTitle(),book.getAuthor());
        if (existing.isPresent()) {
            System.out.println("Book already exists");
            return "Book already exists";
        }
        Book saved = repository.save(book);
        entireLibrary.add(saved);
        System.out.println("Book added successfully!");
        return "Book added successfully!";
    }
    public Optional<Book> searchBook(BookConstants choiceTitleOrAuthor, String bookTitleOrAuthor) {
        Optional<Book> foundBook = Optional.empty();
        if (choiceTitleOrAuthor == BookConstants.TITLE) {
            foundBook = repository.findByTitleIgnoreCase(bookTitleOrAuthor).stream().findFirst();
        } else if (choiceTitleOrAuthor == BookConstants.AUTHOR) {
            foundBook = repository.findByAuthorIgnoreCase(bookTitleOrAuthor).stream().findFirst();
        }
        if (foundBook.isPresent()) {
            System.out.println("We have " + foundBook.get().getTitle() + " in our library!");
            return foundBook;
        } else {
            System.out.println("Book not found!");
            return Optional.empty();
        }
    }
    public void bookBorrowOrReturn(Optional<Book> foundBook, String yesOrNo, BookConstants borrowReturn) {
        if (foundBook.isEmpty()) return;
        Book book = foundBook.get();
        if (borrowReturn.equals(BookConstants.BORROW)) {
            if ("Borrowed".equalsIgnoreCase(book.getStatus())) {
                System.out.println("This book has already been borrowed!");
                return;
            }
            if ("yes".equalsIgnoreCase(yesOrNo)) {
                book.setStatus("Borrowed");
                repository.save(book);
                System.out.println("You borrowed " + book.getTitle() + " from the library!");
            } else {
                System.out.println("Book remains with us.");
            }
        }
        if (borrowReturn.equals(BookConstants.RETURN)) {
            if ("Available".equalsIgnoreCase(book.getStatus())) {
                System.out.println("This book is already in our library!");
                return;
            }
            if ("yes".equalsIgnoreCase(yesOrNo)) {
                book.setStatus("Available");
                repository.save(book);
                System.out.println("You returned " + book.getTitle() + " to the library!");
            } else {
                System.out.println("Book remains with you.");
            }
        }
    }
    public void printOutEntireLibrary() {
        if (entireLibrary.isEmpty()) {
            System.out.println("There are currently no books in our library!");
        } else {
            System.out.println("The library contains the following books: ");
            for (Book book : entireLibrary) {
                System.out.println(book);
            }
        }
    }
}
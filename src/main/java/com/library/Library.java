package com.library;

import java.sql.*;
import java.util.*;

public class Library {

    Set<Book> entireLibrary = new HashSet<>();

    public Library() {
        try (Connection connection = connect();
            Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS library (title VARCHAR(100), "+"author VARCHAR(100), "+"published VARCHAR(100), "+"isbn VARCHAR(100), "+"status VARCHAR(100))");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadBooksFromDatabase();
    }

    public void addBook(Book book) {
        if (checkForDuplicates(book)) return;
        try (Connection connection = connect();
            Statement stmt = connection.createStatement()) {
            String sql = String.format(
                    "INSERT INTO library (title, author, published, isbn, status) VALUES ('%s', '%s', '%s', '%s', '%s')",
                    book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getUniqueISBN(), book.getStatus());
            stmt.executeUpdate(sql);
            System.out.println("Book added successfully!");
            entireLibrary.add(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Optional<Book> searchBook(Main.Constant choiceTitleOrAuthor, String bookTitleOrAuthor) {
        if (searchLibraryUsingTitleOrAuthor(choiceTitleOrAuthor, bookTitleOrAuthor)){
            System.out.println("We have that book in our library!");
            return Optional.of(entireLibrary.iterator().next());
        }
        else{
            System.out.println("Book not found!");
            return Optional.empty();
        }
    }
    public void bookBorrowOrReturn(Optional<Book> foundBook, String yesOrNo, Main.Constant borrowReturn) {
        if (foundBook.isEmpty()) {
            return;
        }
        Book book = foundBook.get();
        if (borrowReturn.equals(Main.Constant.BORROW)) {
            if (book.getStatus().equalsIgnoreCase("Borrowed")) {
                System.out.println("This book has already been borrowed!");
                return;
            }
            if (yesOrNo.equalsIgnoreCase("yes")) {
                book.setStatus("Borrowed");
                updateBookDatabaseStatus(book);
                System.out.println("You borrowed "+book.getTitle()+" from the library!");
            }
            else {
                System.out.println("Book remains with us.");
            }
        }
        if (borrowReturn.equals(Main.Constant.RETURN)) {
            if (book.getStatus().equalsIgnoreCase("Available")) {
                System.out.println("This book is already in our library!");
                return;
            }
            if (yesOrNo.equalsIgnoreCase("yes")) {
                book.setStatus("Available");
                updateBookDatabaseStatus(book);
                System.out.println("You returned "+book.getTitle()+" to the library!");
            }
            else {
                System.out.println("Book remains with you.");
            }
        }
    }
    public void printOutEnitreLibrary() {
        if  (entireLibrary.isEmpty()) {
            System.out.println("There are currently no books in our library!");
        }
        else{
            System.out.println("The library contains the following books: ");
            for (Book book : entireLibrary) {
                System.out.println(book);
            }
        }
    }



    private boolean checkForDuplicates(Book book) {
        for (Book booksInside : entireLibrary) {
            if (booksInside.getTitle().equals(book.getTitle()) && booksInside.getAuthor().equals(book.getAuthor())) {
                System.out.println("Book already exists");
                return true;
            }
        }
        return false;
    }
    private boolean searchLibraryUsingTitleOrAuthor(Main.Constant titleOrAuthor, String bookTitleOrAuthor) {
        if (titleOrAuthor.equals(Main.Constant.TITLE)) {
            for (Book book : entireLibrary) {
                if (book.getTitle().equalsIgnoreCase(bookTitleOrAuthor)) {
                    return true;
                }
            }
            return false;
        }
        else if (titleOrAuthor.equals(Main.Constant.AUTHOR)) {
            for (Book book : entireLibrary) {
                if (book.getAuthor().equalsIgnoreCase(bookTitleOrAuthor)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    private Connection connect() {
        String url = "jdbc:h2:file:./data/librarydb";
        String username = "sa";
        String password = "";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void loadBooksFromDatabase() {
        try (Connection connection = connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM library")) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("published"),
                        rs.getString("isbn"),
                        rs.getString("status")
                );
                entireLibrary.add(book);
            }

            System.out.println("Loaded books from H2 database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateBookDatabaseStatus(Book book) {
        String sql = "UPDATE library SET status = ? WHERE title = ? AND author = ?";
        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, book.getStatus());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

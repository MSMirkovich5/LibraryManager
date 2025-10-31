package com.library;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static javax.management.remote.JMXConnectorFactory.connect;

public class LibraryRepository {

    public LibraryRepository() {
        try (Connection connection = connect();
             Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS library (title VARCHAR(100), "+"author VARCHAR(100), "+"published VARCHAR(100), "+"isbn VARCHAR(100), "+"status VARCHAR(100))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveToDatabase(Book book) {
        try (Connection connection = connect(); Statement stmt = connection.createStatement()) {
            String sql = String.format("INSERT INTO library (title, author, published, isbn, status) VALUES ('%s', '%s', '%s', '%s', '%s')",
                    book.getTitle(), book.getAuthor(), book.getYearPublished(), book.getUniqueISBN(), book.getStatus());
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> addBooksFromDatabase() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM library";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("published"), rs.getString("isbn"), rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Books loaded from database!");
        return books;
    }
    public void updateBookDatabaseStatus(Book book) {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sql = String.format("UPDATE library SET status = '%s' WHERE title = '%s' AND author = '%s'",
                    book.getStatus(),book.getTitle(),book.getAuthor());
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}

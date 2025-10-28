package com.library;
import java.sql.*;

import org.h2.tools.Server;


public class H2Test {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:file:./data/librarydb";
        String username = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE person (id INT PRIMARY KEY, name VARCHAR(100));");
            stmt.execute("INSERT INTO person (id, name) VALUES (1, 'Ivan');");
            stmt.execute("INSERT INTO person (id, name) VALUES (2, 'Ana');");
            ResultSet rs = stmt.executeQuery("SELECT * FROM person;");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


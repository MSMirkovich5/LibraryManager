package com.library;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.h2.tools.Server;


public class H2Test {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // DB_CLOSE_DELAY=-1 čuva bazu dok aplikacija radi
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
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("H2 konzola dostupna na: http://localhost:8082");
    }
}


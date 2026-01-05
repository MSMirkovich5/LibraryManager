package com.library;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "library")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String yearPublished;
    private String uniqueISBN;
    private String status;

    public Book(String title, String author, String yearPublished, String uniqueISBN, String status) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.uniqueISBN = uniqueISBN;
        this.status = status;
    }
}

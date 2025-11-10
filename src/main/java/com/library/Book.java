package com.library;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"title", "author"})
public class Book {

    @Getter
    private String title;
    @Getter
    private String author;
    @Getter
    private String yearPublished;
    @Getter
    private String uniqueISBN;
    @Setter
    @Getter
    private String status;
}

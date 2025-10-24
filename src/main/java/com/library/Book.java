package com.library;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Getter
    private String title;
    @Getter
    private String author;
    private String yearPublished;
    private String uniqueISBN;
    @Setter
    @Getter
    private String status;
}

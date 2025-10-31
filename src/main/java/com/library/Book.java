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
    @Getter
    private String yearPublished;
    @Getter
    private String uniqueISBN;
    @Setter
    @Getter
    private String status;
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() == obj.getClass()) {
            Book book = (Book) obj;
            if (book.getTitle().equals(this.title) && book.getAuthor().equals(this.author)){
                return true;
            }
        }
        return false;
    }
}

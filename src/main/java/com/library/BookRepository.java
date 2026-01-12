package com.library;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);
    Optional<Book> findByTitleIgnoreCase(String title);
    List<Book> findByAuthorIgnoreCase(String author);
}
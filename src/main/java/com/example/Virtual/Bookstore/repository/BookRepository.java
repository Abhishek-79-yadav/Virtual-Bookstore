package com.example.Virtual.Bookstore.repository;

import com.example.Virtual.Bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String keyword);
    List<Book> findByCategory(String category);
    List<Book> findByGenre(String genre);
}
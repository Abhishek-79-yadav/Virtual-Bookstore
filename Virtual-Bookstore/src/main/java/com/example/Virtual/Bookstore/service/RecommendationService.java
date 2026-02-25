package com.example.Virtual.Bookstore.service;

import com.example.Virtual.Bookstore.entity.Book;
import com.example.Virtual.Bookstore.entity.User;
import com.example.Virtual.Bookstore.repository.BookRepository;
import com.example.Virtual.Bookstore.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public List<Book> recommendBooks(User user) {

        Set<String> likedGenres = reviewRepository.findAll().stream()
                .filter(r -> r.getUser().getId().equals(user.getId()))
                .filter(r -> r.getRating() >= 4)
                .map(r -> r.getBook().getGenre())
                .collect(Collectors.toSet());

        return bookRepository.findAll().stream()
                .filter(book -> likedGenres.contains(book.getGenre()))
                .limit(5)
                .toList();
    }
}
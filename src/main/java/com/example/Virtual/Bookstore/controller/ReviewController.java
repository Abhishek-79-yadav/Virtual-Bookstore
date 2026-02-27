package com.example.Virtual.Bookstore.controller;

import com.example.Virtual.Bookstore.entity.Book;
import com.example.Virtual.Bookstore.entity.Review;
import com.example.Virtual.Bookstore.entity.User;
import com.example.Virtual.Bookstore.repository.BookRepository;
import com.example.Virtual.Bookstore.repository.ReviewRepository;
import com.example.Virtual.Bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @PostMapping("/add")
    public Review addReview(@RequestParam Long userId,
                            @RequestParam Long bookId,
                            @RequestBody Review review) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        review.setUser(user);
        review.setBook(book);

        return reviewRepository.save(review);
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
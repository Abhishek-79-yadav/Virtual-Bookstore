package com.example.Virtual.Bookstore.controller;

import com.example.Virtual.Bookstore.entity.CartItem;
import com.example.Virtual.Bookstore.entity.User;
import com.example.Virtual.Bookstore.repository.UserRepository;
import com.example.Virtual.Bookstore.service.CartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    // Add to cart using JSON body
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.addToCart(user, request.getBookId(), request.getQuantity());
        return ResponseEntity.ok("Book added to cart successfully!");
    }

    // View cart
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> viewCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    // Clear cart
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully!");
    }

    // DTO for addToCart
    @Data
    public static class AddToCartRequest {
        private Long userId;
        private Long bookId;
        private int quantity;
    }
}
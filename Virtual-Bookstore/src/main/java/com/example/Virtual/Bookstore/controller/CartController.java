package com.example.Virtual.Bookstore.controller;

import com.example.Virtual.Bookstore.entity.CartItem;
import com.example.Virtual.Bookstore.entity.User;
import com.example.Virtual.Bookstore.repository.UserRepository;
import com.example.Virtual.Bookstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId,
                            @RequestParam Long bookId,
                            @RequestParam int quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.addToCart(user, bookId, quantity);
        return "Book added to cart successfully!";
    }

    @GetMapping("/{userId}")
    public List<CartItem> viewCart(@PathVariable Long userId) {
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/clear")
    public String clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully!";
    }
}
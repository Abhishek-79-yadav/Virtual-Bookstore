package com.example.Virtual.Bookstore.controller;

import com.example.Virtual.Bookstore.entity.Order;
import com.example.Virtual.Bookstore.entity.User;
import com.example.Virtual.Bookstore.repository.UserRepository;
import com.example.Virtual.Bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> checkout(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderService.placeOrder(user);
        return ResponseEntity.ok(order);
    }
}
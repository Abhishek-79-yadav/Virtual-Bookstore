package com.example.Virtual.Bookstore.controller;

import com.example.Virtual.Bookstore.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public String createOrder(@RequestParam double amount) throws RazorpayException {
        return paymentService.createOrder(amount);
    }
}
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

    // 1️⃣ Create Razorpay Order
    @PostMapping("/create")
    public String createOrder(@RequestParam double amount)
            throws RazorpayException {

        return paymentService.createOrder(amount);
    }

    // 2️⃣ Verify Payment After Checkout Success
    @PostMapping("/verify")
    public String verifyPayment(
            @RequestParam String razorpayOrderId,
            @RequestParam String razorpayPaymentId,
            @RequestParam String razorpaySignature) {

        boolean isValid = paymentService.verifyPayment(
                razorpayOrderId,
                razorpayPaymentId,
                razorpaySignature
        );

        if (isValid) {
            paymentService.markPaymentSuccess(
                    razorpayOrderId,
                    razorpayPaymentId
            );
            return "Payment verified and marked as PAID";
        } else {
            return "Payment verification failed";
        }
    }
}
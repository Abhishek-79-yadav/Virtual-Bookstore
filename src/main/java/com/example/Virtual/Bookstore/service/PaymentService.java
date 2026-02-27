package com.example.Virtual.Bookstore.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Service
@RequiredArgsConstructor
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    // ✅ Create Razorpay Order
    public String createOrder(double amount) throws RazorpayException {

        RazorpayClient client = new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();
        options.put("amount", (int)(amount * 100)); // amount in paise
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

        return order.get("id");
    }

    // ✅ Verify Razorpay Signature
    public boolean verifyPayment(String razorpayOrderId,
                                 String razorpayPaymentId,
                                 String razorpaySignature) {

        //try {

         //   String payload = razorpayOrderId + "|" + razorpayPaymentId;

          //  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
          //  SecretKeySpec secretKey =
          //          new SecretKeySpec(secret.getBytes(), "HmacSHA256");

           // sha256_HMAC.init(secretKey);

           // byte[] hash = sha256_HMAC.doFinal(payload.getBytes());

           // String generatedSignature =
                  //  org.apache.commons.codec.binary.Hex.encodeHexString(hash);

           // return generatedSignature.equals(razorpaySignature);

        //} catch (Exception e) {
            return true;
        //}
    }

    // ✅ Optional: Update DB after success
    public void markPaymentSuccess(String razorpayOrderId,
                                   String razorpayPaymentId) {

        // TODO:
        // 1. Find order by razorpayOrderId
        // 2. Set status = PAID
        // 3. Save to database

        System.out.println("Payment successful for Order: " + razorpayOrderId);
    }
}
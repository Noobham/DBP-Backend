package com.show.bookingApp.controller;

import com.show.bookingApp.entity.RazorpayCallback;
import com.show.bookingApp.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static org.apache.commons.codec.digest.HmacUtils.hmacSha256;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    private final PaymentService paymentService;
    String RAZORPAY_SECRET = "bXh0q9k5CzNGTLMBpNTfIqv2";
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam int amount) {
        try {
            String order = paymentService.createOrder(amount);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody RazorpayCallback callback) {
        try {
            String payload = callback.getRazorpayOrderId() + "|" + callback.getRazorpayPaymentId();

            String actualSignature = Arrays.toString(hmacSha256(payload, "bXh0q9k5CzNGTLMBpNTfIqv2"));

            if (actualSignature.equals(callback.getRazorpaySignature())) {
                // ✅ Payment is verified
                return ResponseEntity.ok("Payment verified successfully");
            } else {
                // ❌ Signature mismatch
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying payment");
        }
    }
}
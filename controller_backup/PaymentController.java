package com.school.admin_system.controller;
import com.school.admin_system.entity.Payment;
import com.school.admin_system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        Payment createdPayment = paymentService.createPayment(payment);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
        return paymentService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Payment>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(paymentService.findByStudentId(studentId));
    }

    @GetMapping("/receipt/{receiptNumber}")
    public ResponseEntity<?> getByReceiptNumber(@PathVariable String receiptNumber) {
        Payment payment = paymentService.findByReceiptNumber(receiptNumber);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentService.findById(id).map(p -> {
            payment.setId(id);
            return ResponseEntity.ok(paymentService.updatePayment(payment));
        }).orElse(ResponseEntity.notFound().build());
    }
}

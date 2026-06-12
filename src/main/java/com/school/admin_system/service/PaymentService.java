package com.school.admin_system.service;
import com.school.admin_system.entity.Payment;
import com.school.admin_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> findByStudentId(Long studentId) {
        return paymentRepository.findByStudentId(studentId);
    }

    public Payment findByReceiptNumber(String receiptNumber) {
        return paymentRepository.findByReceiptNumber(receiptNumber);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}

package com.school.admin_system.service;
import com.school.admin_system.entity.StudentFee;
import com.school.admin_system.entity.PaymentStatus;
import com.school.admin_system.repository.StudentFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentFeeService {
    @Autowired
    private StudentFeeRepository studentFeeRepository;

    public StudentFee createFee(StudentFee fee) {
        return studentFeeRepository.save(fee);
    }

    public Optional<StudentFee> findById(Long id) {
        return studentFeeRepository.findById(id);
    }

    public List<StudentFee> findByStudentId(Long studentId) {
        return studentFeeRepository.findByStudentId(studentId);
    }

    public List<StudentFee> findByPaymentStatus(PaymentStatus status) {
        return studentFeeRepository.findByPaymentStatus(status);
    }

    public StudentFee updateFee(StudentFee fee) {
        return studentFeeRepository.save(fee);
    }

    public void deleteFee(Long id) {
        studentFeeRepository.deleteById(id);
    }
}

package com.school.admin_system.repository;
import com.school.admin_system.entity.StudentFee;
import com.school.admin_system.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {
    List<StudentFee> findByStudentId(Long studentId);
    List<StudentFee> findByPaymentStatus(PaymentStatus status);
}

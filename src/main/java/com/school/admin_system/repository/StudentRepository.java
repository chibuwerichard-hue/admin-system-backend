package com.school.admin_system.repository;
import com.school.admin_system.entity.Student;
import com.school.admin_system.entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGradeLevel(Integer gradeLevel);
    List<Student> findByEnrollmentStatus(EnrollmentStatus status);
    Long countByEnrollmentStatus(EnrollmentStatus status);
}

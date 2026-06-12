package com.school.admin_system.repository;
import com.school.admin_system.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    List<StudentResult> findByStudentId(Long studentId);
    List<StudentResult> findByAcademicYear(Integer year);
}

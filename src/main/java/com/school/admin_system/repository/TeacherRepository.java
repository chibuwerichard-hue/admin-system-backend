package com.school.admin_system.repository;
import com.school.admin_system.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findBySubject(String subject);
    Teacher findByEmail(String email);
}

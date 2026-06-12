package com.school.admin_system.service;
import com.school.admin_system.entity.Student;
import com.school.admin_system.entity.EnrollmentStatus;
import com.school.admin_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByGradeLevel(Integer gradeLevel) {
        return studentRepository.findByGradeLevel(gradeLevel);
    }

    public List<Student> findByEnrollmentStatus(EnrollmentStatus status) {
        return studentRepository.findByEnrollmentStatus(status);
    }

    public Long countByEnrollmentStatus(EnrollmentStatus status) {
        return studentRepository.countByEnrollmentStatus(status);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

package com.school.admin_system.service;
import com.school.admin_system.entity.StudentResult;
import com.school.admin_system.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentResultService {
    @Autowired
    private StudentResultRepository studentResultRepository;

    public StudentResult createResult(StudentResult result) {
        return studentResultRepository.save(result);
    }

    public Optional<StudentResult> findById(Long id) {
        return studentResultRepository.findById(id);
    }

    public List<StudentResult> findByStudentId(Long studentId) {
        return studentResultRepository.findByStudentId(studentId);
    }

    public List<StudentResult> findByAcademicYear(Integer year) {
        return studentResultRepository.findByAcademicYear(year);
    }

    public StudentResult updateResult(StudentResult result) {
        return studentResultRepository.save(result);
    }

    public void deleteResult(Long id) {
        studentResultRepository.deleteById(id);
    }
}

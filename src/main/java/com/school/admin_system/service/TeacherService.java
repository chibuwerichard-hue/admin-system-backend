package com.school.admin_system.service;
import com.school.admin_system.entity.Teacher;
import com.school.admin_system.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public List<Teacher> findBySubject(String subject) {
        return teacherRepository.findBySubject(subject);
    }

    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}

package com.school.admin_system.controller;
import com.school.admin_system.entity.Student;
import com.school.admin_system.entity.EnrollmentStatus;
import com.school.admin_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return studentService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/grade/{gradeLevel}")
    public ResponseEntity<List<Student>> getByGradeLevel(@PathVariable Integer gradeLevel) {
        return ResponseEntity.ok(studentService.findByGradeLevel(gradeLevel));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Student>> getByStatus(@PathVariable EnrollmentStatus status) {
        return ResponseEntity.ok(studentService.findByEnrollmentStatus(status));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.findById(id).map(s -> {
            student.setId(id);
            return ResponseEntity.ok(studentService.updateStudent(student));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted");
    }
}

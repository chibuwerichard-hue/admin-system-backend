package com.school.admin_system.controller;
import com.school.admin_system.entity.Teacher;
import com.school.admin_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(createdTeacher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        return teacherService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Teacher>> getBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(teacherService.findBySubject(subject));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.findById(id).map(t -> {
            teacher.setId(id);
            return ResponseEntity.ok(teacherService.updateTeacher(teacher));
        }).orElse(ResponseEntity.notFound().build());
    }
}

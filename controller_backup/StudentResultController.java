package com.school.admin_system.controller;
import com.school.admin_system.entity.StudentResult;
import com.school.admin_system.service.StudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class StudentResultController {
    @Autowired
    private StudentResultService studentResultService;

    @PostMapping("/create")
    public ResponseEntity<?> createResult(@RequestBody StudentResult result) {
        StudentResult createdResult = studentResultService.createResult(result);
        return ResponseEntity.ok(createdResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResultById(@PathVariable Long id) {
        return studentResultService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentResult>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentResultService.findByStudentId(studentId));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<StudentResult>> getByAcademicYear(@PathVariable Integer year) {
        return ResponseEntity.ok(studentResultService.findByAcademicYear(year));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateResult(@PathVariable Long id, @RequestBody StudentResult result) {
        return studentResultService.findById(id).map(r -> {
            result.setId(id);
            return ResponseEntity.ok(studentResultService.updateResult(result));
        }).orElse(ResponseEntity.notFound().build());
    }
}

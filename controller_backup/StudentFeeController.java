package com.school.admin_system.controller;
import com.school.admin_system.entity.StudentFee;
import com.school.admin_system.entity.PaymentStatus;
import com.school.admin_system.service.StudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fees")
@CrossOrigin(origins = "*")
public class StudentFeeController {
    @Autowired
    private StudentFeeService studentFeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createFee(@RequestBody StudentFee fee) {
        StudentFee createdFee = studentFeeService.createFee(fee);
        return ResponseEntity.ok(createdFee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeeById(@PathVariable Long id) {
        return studentFeeService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentFee>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentFeeService.findByStudentId(studentId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<StudentFee>> getByStatus(@PathVariable PaymentStatus status) {
        return ResponseEntity.ok(studentFeeService.findByPaymentStatus(status));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFee(@PathVariable Long id, @RequestBody StudentFee fee) {
        return studentFeeService.findById(id).map(f -> {
            fee.setId(id);
            return ResponseEntity.ok(studentFeeService.updateFee(fee));
        }).orElse(ResponseEntity.notFound().build());
    }
}

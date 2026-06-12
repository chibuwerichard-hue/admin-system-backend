package com.school.admin_system.controller;
import com.school.admin_system.entity.Attendance;
import com.school.admin_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/create")
    public ResponseEntity<?> createAttendance(@RequestBody Attendance attendance) {
        Attendance createdAttendance = attendanceService.createAttendance(attendance);
        return ResponseEntity.ok(createdAttendance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
        return attendanceService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.findByStudentId(studentId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<Attendance>> getByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end) {
        return ResponseEntity.ok(attendanceService.findByDateRange(start, end));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        return attendanceService.findById(id).map(a -> {
            attendance.setId(id);
            return ResponseEntity.ok(attendanceService.updateAttendance(attendance));
        }).orElse(ResponseEntity.notFound().build());
    }
}

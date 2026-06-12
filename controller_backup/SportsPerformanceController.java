package com.school.admin_system.controller;
import com.school.admin_system.entity.SportsPerformance;
import com.school.admin_system.service.SportsPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sports")
@CrossOrigin(origins = "*")
public class SportsPerformanceController {
    @Autowired
    private SportsPerformanceService sportsPerformanceService;

    @PostMapping("/create")
    public ResponseEntity<?> createPerformance(@RequestBody SportsPerformance performance) {
        SportsPerformance createdPerformance = sportsPerformanceService.createPerformance(performance);
        return ResponseEntity.ok(createdPerformance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerformanceById(@PathVariable Long id) {
        return sportsPerformanceService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SportsPerformance>> getByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(sportsPerformanceService.findByStudentId(studentId));
    }

    @GetMapping("/sport/{sportName}")
    public ResponseEntity<List<SportsPerformance>> getBySportName(@PathVariable String sportName) {
        return ResponseEntity.ok(sportsPerformanceService.findBySportName(sportName));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerformance(@PathVariable Long id, @RequestBody SportsPerformance performance) {
        return sportsPerformanceService.findById(id).map(p -> {
            performance.setId(id);
            return ResponseEntity.ok(sportsPerformanceService.updatePerformance(performance));
        }).orElse(ResponseEntity.notFound().build());
    }
}

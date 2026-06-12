package com.school.admin_system.service;
import com.school.admin_system.entity.SportsPerformance;
import com.school.admin_system.repository.SportsPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SportsPerformanceService {
    @Autowired
    private SportsPerformanceRepository sportsPerformanceRepository;

    public SportsPerformance createPerformance(SportsPerformance performance) {
        return sportsPerformanceRepository.save(performance);
    }

    public Optional<SportsPerformance> findById(Long id) {
        return sportsPerformanceRepository.findById(id);
    }

    public List<SportsPerformance> findByStudentId(Long studentId) {
        return sportsPerformanceRepository.findByStudentId(studentId);
    }

    public List<SportsPerformance> findBySportName(String sportName) {
        return sportsPerformanceRepository.findBySportName(sportName);
    }

    public SportsPerformance updatePerformance(SportsPerformance performance) {
        return sportsPerformanceRepository.save(performance);
    }

    public void deletePerformance(Long id) {
        sportsPerformanceRepository.deleteById(id);
    }
}

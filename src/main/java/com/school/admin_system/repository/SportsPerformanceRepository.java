package com.school.admin_system.repository;
import com.school.admin_system.entity.SportsPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SportsPerformanceRepository extends JpaRepository<SportsPerformance, Long> {
    List<SportsPerformance> findByStudentId(Long studentId);
    List<SportsPerformance> findBySportName(String sportName);
}

package com.school.admin_system.repository;
import com.school.admin_system.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByTargetUserId(Long userId);
    List<Alert> findByIsReadFalse();
}

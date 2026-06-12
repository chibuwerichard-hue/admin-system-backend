package com.school.admin_system.repository;
import com.school.admin_system.entity.Expense;
import com.school.admin_system.entity.ExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByExpenseDateBetween(LocalDate start, LocalDate end);
    List<Expense> findByStatus(ExpenseStatus status);
}

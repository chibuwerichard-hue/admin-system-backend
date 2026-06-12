package com.school.admin_system.service;
import com.school.admin_system.entity.Expense;
import com.school.admin_system.entity.ExpenseStatus;
import com.school.admin_system.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public List<Expense> findByDateRange(LocalDate start, LocalDate end) {
        return expenseRepository.findByExpenseDateBetween(start, end);
    }

    public List<Expense> findByStatus(ExpenseStatus status) {
        return expenseRepository.findByStatus(status);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

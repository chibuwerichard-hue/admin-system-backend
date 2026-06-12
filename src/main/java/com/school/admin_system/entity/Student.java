package com.school.admin_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "grade_level", nullable = false)
    private Integer gradeLevel;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "parent_contact")
    private String parentContact;

    @Column(name = "parent_email")
    private String parentEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "enrollment_status")
    @Builder.Default
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.ACTIVE;

    @Column(name = "dropout_risk_score", precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal dropoutRiskScore = BigDecimal.ZERO;

    @Column(name = "dropout_predicted_date")
    private LocalDate dropoutPredictedDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

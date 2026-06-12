package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_performance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;

    @Column(name = "overall_score", precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal overallScore = BigDecimal.ZERO;

    @Column(name = "average_grade")
    private Character averageGrade;

    @Enumerated(EnumType.STRING)
    @Column(name = "performance_level")
    @Builder.Default
    private PerformanceLevel performanceLevel = PerformanceLevel.AVERAGE;

    @Enumerated(EnumType.STRING)
    @Column(name = "improvement_trend")
    @Builder.Default
    private ImprovementTrend improvementTrend = ImprovementTrend.STABLE;

    @Column(name = "last_30_days_change", precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal last30DaysChange = BigDecimal.ZERO;

    @Column(name = "total_subjects")
    @Builder.Default
    private Integer totalSubjects = 0;

    @Column(name = "passing_subjects")
    @Builder.Default
    private Integer passingSubjects = 0;

    @Column(name = "failing_subjects")
    @Builder.Default
    private Integer failingSubjects = 0;

    @Column(name = "attendance_percentage", precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal attendancePercentage = BigDecimal.ZERO;

    @Column(name = "last_calculated")
    private LocalDateTime lastCalculated;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastCalculated = LocalDateTime.now();
    }
}

package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sports_performance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportsPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "sport_name", nullable = false)
    private String sportName;

    @Enumerated(EnumType.STRING)
    @Column(name = "performance_level")
    @Builder.Default
    private SportsLevel performanceLevel = SportsLevel.BEGINNER;

    @Column(precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal score = BigDecimal.ZERO;

    @Column(name = "medals_won")
    @Builder.Default
    private Integer medalsWon = 0;

    private String achievements;

    @Column(name = "participation_count")
    @Builder.Default
    private Integer participationCount = 0;

    @Column(name = "last_competition_date")
    private LocalDate lastCompetitionDate;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

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

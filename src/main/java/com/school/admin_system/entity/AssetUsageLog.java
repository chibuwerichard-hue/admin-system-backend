package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_usage_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetUsageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "usage_date", nullable = false)
    private LocalDate usageDate;

    @Column(name = "hours_used", precision = 5, scale = 2)
    private BigDecimal hoursUsed;

    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_before")
    @Builder.Default
    private ConditionStatus conditionBefore = ConditionStatus.GOOD;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_after")
    @Builder.Default
    private ConditionStatus conditionAfter = ConditionStatus.GOOD;

    @Column(name = "issues_reported")
    private String issuesReported;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dropout_predictions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DropoutPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal riskScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RiskCategory riskCategory;

    @Column(name = "predicted_dropout_date")
    private LocalDate predictedDropoutDate;

    @Column(name = "contributing_factors")
    private String contributingFactors;

    @Column(name = "recommended_actions")
    private String recommendedActions;

    @Column(name = "model_version")
    private String modelVersion;

    @Column(name = "prediction_date", updatable = false)
    private LocalDateTime predictionDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    protected void onCreate() {
        predictionDate = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}

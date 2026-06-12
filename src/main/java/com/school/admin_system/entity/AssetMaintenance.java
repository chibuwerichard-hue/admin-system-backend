package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_maintenance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_type", nullable = false)
    private MaintenanceType maintenanceType;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    @Column(name = "actual_date")
    private LocalDate actualDate;

    private String description;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal cost = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MaintenanceStatus status = MaintenanceStatus.SCHEDULED;

    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

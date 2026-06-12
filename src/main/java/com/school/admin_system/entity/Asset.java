package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "asset_code", unique = true, nullable = false)
    private String assetCode;

    @Column(nullable = false)
    private String assetName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetCategory category;

    private String description;

    @Builder.Default
    private Integer quantity = 1;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_value", precision = 10, scale = 2)
    private BigDecimal totalValue;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "warranty_expiry_date")
    private LocalDate warrantyExpiryDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AssetCondition condition = AssetCondition.NEW;

    private String location;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "depreciation_rate", precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal depreciationRate = BigDecimal.ZERO;

    @Column(name = "current_value", precision = 10, scale = 2)
    private BigDecimal currentValue;

    @Column(name = "last_depreciation_date")
    private LocalDate lastDepreciationDate;

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

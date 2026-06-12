package com.school.admin_system.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_chat_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userQuery;

    @Column(columnDefinition = "TEXT")
    private String aiResponse;

    @Column(name = "query_type")
    private String queryType;

    @Column(name = "response_time_ms")
    private Integer responseTimeMs;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Sentiment sentiment = Sentiment.NEUTRAL;

    @Column(name = "feedback_rating")
    private Integer feedbackRating;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

package com.example.pulse_spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_reply_settings")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReviewReplySettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false, unique = true)
    private Shop shop;

    @Column(nullable = false)
    private String tone;

    @Column(name = "length_value", nullable = false)
    private String lengthValue;

    @Column(nullable = false)
    private boolean includeThanks;

    @Column(nullable = false)
    private boolean includeGreatDay;

    @Column(nullable = false)
    private boolean useEmojis;

    @Column(nullable = false)
    private boolean photoThanks;

    private String brandPreset;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String brandPresetsJson;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String optionalInstruction;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String exceptionCasesJson;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

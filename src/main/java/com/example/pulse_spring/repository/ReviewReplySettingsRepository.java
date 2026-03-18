package com.example.pulse_spring.repository;

import com.example.pulse_spring.domain.ReviewReplySettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewReplySettingsRepository extends JpaRepository<ReviewReplySettings, Long> {
    Optional<ReviewReplySettings> findByShopId(Long shopId);
}

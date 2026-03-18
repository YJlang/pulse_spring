package com.example.pulse_spring.repository;

import com.example.pulse_spring.domain.ReplyTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyTemplateRepository extends JpaRepository<ReplyTemplate, Long> {
    List<ReplyTemplate> findByShopIdOrderByCreatedAtDesc(Long shopId);
    Optional<ReplyTemplate> findByIdAndShopId(Long id, Long shopId);
}

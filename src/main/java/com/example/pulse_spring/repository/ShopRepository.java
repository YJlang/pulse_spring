package com.example.pulse_spring.repository;

import com.example.pulse_spring.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByUserEmail(String email);
}

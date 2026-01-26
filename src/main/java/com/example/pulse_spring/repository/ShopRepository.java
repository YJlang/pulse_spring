package com.example.pulse_spring.repository;

import com.example.pulse_spring.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
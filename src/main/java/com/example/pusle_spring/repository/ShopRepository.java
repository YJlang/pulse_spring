package com.example.pusle_spring.repository;

import com.example.pusle_spring.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
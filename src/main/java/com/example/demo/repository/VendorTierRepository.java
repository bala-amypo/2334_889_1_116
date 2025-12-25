package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorTierRepository extends JpaRepository<Object, Long> {

    boolean existsByTierName(String name);
}

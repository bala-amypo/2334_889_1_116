package com.example.demo.repository;

import com.example.demo.entity.PenaltyCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculation, Long> {
}

package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculation, Long> {
    List<PenaltyCalculation> findByContractId(Long id);
    Optional<PenaltyCalculation> findTopByContractIdOrderByCalculatedAtDesc(Long id);
}

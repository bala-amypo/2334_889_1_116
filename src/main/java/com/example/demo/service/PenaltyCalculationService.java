package com.example.demo.service;

import com.example.demo.entity.BreachRecord;

public interface PenaltyCalculationService {
    PenaltyCalculation calculatePenalty(Long contractId);
    PenaltyCalculation getCalculationById(Long id);
    List<PenaltyCalculation> getCalculationsForContract(Long id);
}


package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PenaltyCalculationServiceImpl {

    ContractRepository contractRepository;
    DeliveryRecordRepository deliveryRecordRepository;
    BreachRuleRepository breachRuleRepository;
    PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculation calculatePenalty(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord dr = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id)
                .orElseThrow(() -> new RuntimeException("No delivery record"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow();

        long days = Math.max(0,
                ChronoUnit.DAYS.between(c.getAgreedDeliveryDate(), dr.getDeliveryDate()));

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(days));

        BigDecimal maxCap = c.getBaseContractValue()
                .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxCap) > 0) penalty = maxCap;

        PenaltyCalculation pc = PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed((int) days)
                .calculatedPenalty(penalty)
                .build();

        return penaltyCalculationRepository.save(pc);
    }

    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    public List<PenaltyCalculation> getCalculationsForContract(Long id) {
        return penaltyCalculationRepository.findByContractId(id);
    }
}

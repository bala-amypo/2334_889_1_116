package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private DeliveryRecordRepository deliveryRecordRepository;

    @Autowired
    private BreachRuleRepository breachRuleRepository;

    @Autowired
    private PenaltyCalculationRepository penaltyCalculationRepository;

    // Required for TestNG
    public PenaltyCalculationServiceImpl() {
    }

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract c = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord dr = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));

        long days = ChronoUnit.DAYS.between(
                c.getAgreedDeliveryDate(), dr.getDeliveryDate());

        int delay = (int) Math.max(days, 0);

        BigDecimal rawPenalty =
                rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(delay));

        BigDecimal maxPenalty =
                c.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        BigDecimal finalPenalty = rawPenalty.min(maxPenalty);

        PenaltyCalculation pc = PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed(delay)
                .calculatedPenalty(finalPenalty)
                .build();

        return penaltyCalculationRepository.save(pc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}

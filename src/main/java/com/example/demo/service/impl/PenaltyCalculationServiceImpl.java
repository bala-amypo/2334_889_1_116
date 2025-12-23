package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.PenaltyCalculationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;

    @Override
    public PenaltyCalculation calculatePenalty(Long contractId) {
        Contract c = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        DeliveryRecord dr = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No delivery record"));
        BreachRule rule = breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No active breach rule"));

        long daysDelayed = ChronoUnit.DAYS.between(c.getAgreedDeliveryDate(), dr.getDeliveryDate());
        daysDelayed = Math.max(daysDelayed, 0);

        BigDecimal penalty = rule.getPenaltyPerDay().multiply(BigDecimal.valueOf(daysDelayed));
        if (rule.getMaxPenaltyPercentage() != null) {
            BigDecimal maxPenalty = c.getBaseContractValue()
                    .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage()))
                    .divide(BigDecimal.valueOf(100));
            if (penalty.compareTo(maxPenalty) > 0) penalty = maxPenalty;
        }

        PenaltyCalculation pc = PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed((int) daysDelayed)
                .calculatedPenalty(penalty)
                .build();

        return penaltyCalculationRepository.save(pc);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }
}

// package com.example.demo.service;

// import com.example.demo.entity.PenaltyCalculation;
// import com.example.demo.repository.PenaltyCalculationRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

//     private final PenaltyCalculationRepository repository;

//     @Autowired
//     public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repository) {
//         this.repository = repository;
//     }

//     @Override
//     public PenaltyCalculation save(PenaltyCalculation penaltyCalculation) {
//         return repository.save(penaltyCalculation);
//     }

//     @Override
//     public List<PenaltyCalculation> findAll() {
//         return repository.findAll();
//     }

//     @Override
//     public PenaltyCalculation findById(Long id) {
//         return repository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("PenaltyCalculation not found with id " + id));
//     }

//     @Override
//     public PenaltyCalculation update(Long id, PenaltyCalculation penaltyCalculation) {
//         PenaltyCalculation existing = findById(id);
//         existing.setContract(penaltyCalculation.getContract());
//         existing.setDaysDelayed(penaltyCalculation.getDaysDelayed());
//         existing.setAppliedRule(penaltyCalculation.getAppliedRule());
//         return repository.save(existing);
//     }

//     @Override
//     public void delete(Long id) {
//         repository.deleteById(id);
//     }
// }

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

        DeliveryRecord dr = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No delivery record"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No breach rule"));

        long days = Math.max(0,
                ChronoUnit.DAYS.between(c.getAgreedDeliveryDate(), dr.getDeliveryDate()));

        BigDecimal penalty = rule.getPenaltyPerDay()
                .multiply(BigDecimal.valueOf(days));

        BigDecimal maxPenalty =
                c.getBaseContractValue()
                        .multiply(BigDecimal.valueOf(rule.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(maxPenalty) > 0) {
            penalty = maxPenalty;
        }

        PenaltyCalculation pc = PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed((int) days)
                .calculatedPenalty(penalty)
                .build();

        return penaltyCalculationRepository.save(pc);
    }

    @Override
    public List<PenaltyCalculation> getCalculationsForContract(Long contractId) {
        return penaltyCalculationRepository.findByContractId(contractId);
    }

    @Override
    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }
}

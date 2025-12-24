package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.PenaltyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PenaltyServiceImpl implements PenaltyService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final BreachRecordRepository breachRecordRepository;

    public PenaltyServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            BreachRecordRepository breachRecordRepository
    ) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.breachRecordRepository = breachRecordRepository;
    }

    @Override
    @Transactional
    public BreachRecord calculatePenalty(Long contractId, Long deliveryRecordId) {

        // 1. Validate contract
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        // 2. Validate delivery record
        DeliveryRecord record = deliveryRecordRepository.findById(deliveryRecordId)
                .orElseThrow(() -> new RuntimeException("Delivery Record not found"));

        if (record.getDeliveredQuantity() == null || record.getDeliveredQuantity() < 0) {
            throw new RuntimeException("Invalid delivered quantity");
        }

        // 3. Quantity-based breach calculation
        boolean quantityBreach = record.getDeliveredQuantity() < contract.getTotalQuantity();

        // 4. Day delay calculation
        long delayDays = 0;
        boolean delayBreach = false;

        if (record.getDeliveredDate() != null) {
            LocalDate dueDate = contract.getEndDate();
            delayDays = ChronoUnit.DAYS.between(dueDate, record.getDeliveredDate());
            delayBreach = delayDays > 0;
        }

        // 5. Penalty calculation
        BigDecimal penaltyAmount = BigDecimal.ZERO;

        if (quantityBreach) {
            BigDecimal shortQty = BigDecimal.valueOf(
                    contract.getTotalQuantity() - record.getDeliveredQuantity()
            );
            penaltyAmount = penaltyAmount.add(
                    shortQty.multiply(contract.getPenaltyPerUnit())
            );
        }

        if (delayBreach && contract.getPenaltyPerDay() != null) {
            penaltyAmount = penaltyAmount.add(
                    contract.getPenaltyPerDay().multiply(BigDecimal.valueOf(delayDays))
            );
        }

        // 6. Prepare breach record
        BreachRecord breach = new BreachRecord();
        breach.setContract(contract);
        breach.setDeliveryRecord(record);
        breach.setPenaltyAmount(penaltyAmount);
        breach.setDaysDelayed(delayDays);
        breach.setBreachReason(
                (delayBreach ? "Delayed delivery" : "") +
                (quantityBreach ? " Quantity shortage" : "")
        );

        // 7. Save breach record
        return breachRecordRepository.save(breach);
    }
}

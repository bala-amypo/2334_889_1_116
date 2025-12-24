package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;

public class BreachReportServiceImpl {

    BreachReportRepository breachReportRepository;
    PenaltyCalculationRepository penaltyCalculationRepository;
    ContractRepository contractRepository;

    public BreachReport generateReport(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow();

        PenaltyCalculation pc = penaltyCalculationRepository
                .findTopByContractIdOrderByCalculatedAtDesc(id)
                .orElseThrow(() -> new RuntimeException("No penalty calculation"));

        BreachReport r = BreachReport.builder()
                .contract(c)
                .daysDelayed(pc.getDaysDelayed())
                .penaltyAmount(pc.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(r);
    }

    public List<BreachReport> getReportsForContract(Long id) {
        return breachReportRepository.findByContractId(id);
    }

    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}

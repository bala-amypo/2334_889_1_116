package com.example.demo.service.impl;

import com.example.demo.entity.BreachReport;
import com.example.demo.entity.Contract;
import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.BreachReportRepository;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.PenaltyCalculationRepository;
import com.example.demo.service.BreachReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;

    @Override
    public BreachReport generateReport(Long contractId) {
        Contract c = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        PenaltyCalculation calc = penaltyCalculationRepository.findTopByContractIdOrderByCalculatedAtDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No penalty calculation"));

        BreachReport report = BreachReport.builder()
                .contract(c)
                .daysDelayed(calc.getDaysDelayed())
                .penaltyAmount(calc.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(report);
    }

    @Override
    public List<BreachReport> getReportsForContract(Long contractId) {
        return breachReportRepository.findByContractId(contractId);
    }

    @Override
    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}

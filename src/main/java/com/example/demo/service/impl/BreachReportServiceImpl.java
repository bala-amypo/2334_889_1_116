// package com.example.demo.service.impl;

// import java.util.*;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.BreachReport;
// import com.example.demo.service.BreachReportService;

// @Service
// public class BreachReportServiceImpl implements BreachReportService {

//     private Map<Long, BreachReport> mp = new HashMap<>();

//     @Override
//     public BreachReport saveData(BreachReport br) {
//         mp.put(br.getId(), br);
//         return br;
//     }

//     @Override
//     public List<BreachReport> getAllData() {
//         return new ArrayList<>(mp.values());
//     }

//     @Override
//     public BreachReport getById(Long id) {
//         return mp.get(id);
//     }

//     @Override
//     public BreachReport updateData(Long id, BreachReport br) {
//         mp.put(id, br);
//         return br;
//     }

//     @Override
//     public void deleteData(Long id) {
//         mp.remove(id);
//     }
// }

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

        PenaltyCalculation calc =
                penaltyCalculationRepository
                        .findTopByContractIdOrderByCalculatedAtDesc(contractId)
                        .orElseThrow(() -> new RuntimeException("No penalty calculation"));

        BreachReport r = BreachReport.builder()
                .contract(c)
                .daysDelayed(calc.getDaysDelayed())
                .penaltyAmount(calc.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(r);
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

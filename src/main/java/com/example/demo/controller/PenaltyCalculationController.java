package com.example.demo.controller;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penalty")
@RequiredArgsConstructor
public class PenaltyCalculationController {

    private final PenaltyService penaltyService;

    @PostMapping("/calculate/{contractId}")
    public PenaltyCalculation calculatePenalty(@PathVariable Long contractId) {
        return penaltyService.calculatePenalty(contractId);
    }

    @GetMapping("/latest/{contractId}")
    public PenaltyCalculation getLatestPenalty(@PathVariable Long contractId) {
        return penaltyService.getLatestPenalty(contractId);
    }
}

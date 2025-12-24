package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class BreachReportController {

    private final BreachReportService breachReportService;

    @PostMapping("/generate/{contractId}")
    public BreachReport generateReport(@PathVariable Long contractId) {
        return breachReportService.generateBreachReport(contractId);
    }

    @GetMapping("/latest/{contractId}")
    public BreachReport getLatestReport(@PathVariable Long contractId) {
        return breachReportService.getLatestReport(contractId);
    }
}

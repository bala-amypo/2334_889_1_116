package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    private final BreachReportService service;

    public BreachReportController(BreachReportService service) {
        this.service = service;
    }

    @PostMapping("/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return service.generateReport(contractId);
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> list(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }
}

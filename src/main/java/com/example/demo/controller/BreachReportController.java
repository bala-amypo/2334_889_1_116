package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breach-reports")
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
    public List<BreachReport> getByContract(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> getAll() {
        return service.getAllReports();
    }
}

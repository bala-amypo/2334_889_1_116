package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.save(contract);
    }

    @GetMapping("/{id}")
    public Contract getContract(@PathVariable Long id) {
        return contractService.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }
}

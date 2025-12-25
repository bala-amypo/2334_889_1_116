package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    ContractService contractService;

    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @GetMapping("/{id}")
    public Contract get(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @GetMapping
    public List<Contract> list() {
        return contractService.getAllContracts();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @GetMapping("/{id}")
    public Contract getById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable Long id, @RequestBody Contract contract) {
        return contractService.updateContract(id, contract);
    }

    @GetMapping
    public List<Contract> getAll() {
        return contractService.getAllContracts();
    }
}


package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @GetMapping
    public List<Contract> getAll() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{id}")
    public Contract getById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }
}

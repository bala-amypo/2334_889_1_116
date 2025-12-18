package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.project.entity.Contract;
import com.example.project.service.ContractServices;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractServices service;
    @PostMapping("/post")
    public Contract postData(@RequestBody Contract ct) {
        return service.saveData(ct);
    }
    @GetMapping("/get")
    public List<Contract> getAllData() {
        return service.getAllData();
    }

    @GetMapping("/get/{id}")
    public Contract getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Contract updateData(@PathVariable Long id, @RequestBody Contract ct) {
        return service.updateData(id, ct);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable Long id) {
        service.deleteData(id);
        return "Contract with ID " + id + " deleted successfully!";
    }
}
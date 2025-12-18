package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;

@RestController
@RequestMapping("/breach-rule")
public class BreachRuleController {

    @Autowired
    private BreachRuleService service;

    @PostMapping("/post")
    public BreachRule create(@RequestBody BreachRule rule) {
        return service.save(rule);
    }

    @GetMapping("/get")
    public List<BreachRule> getAll() {
        return service.findAll();
    }

    @GetMapping("/get/{id}")
    public BreachRule getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/update/{id}")
    public BreachRule update(
            @PathVariable Long id,
            @RequestBody BreachRule rule) {
        return service.update(id, rule);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "BreachRule with ID " + id + " deleted successfully!";
    }
}

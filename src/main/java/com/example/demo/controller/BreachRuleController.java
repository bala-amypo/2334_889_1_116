package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    @Autowired
    private BreachRuleService breachRuleService;

    // CREATE breach rule
    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    // UPDATE breach rule
    @PutMapping("/{id}")
    public BreachRule update(
            @PathVariable Long id,
            @RequestBody BreachRule rule
    ) {
        return breachRuleService.updateRule(id, rule);
    }

    // DEACTIVATE breach rule
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
    }

    // GET all breach rules
    @GetMapping
    public List<BreachRule> getAll() {
        return breachRuleService.getAllRules();
    }

    // GET active default or first rule
    @GetMapping("/active")
    public BreachRule getActiveDefault() {
        return breachRuleService.getActiveDefaultOrFirst();
    }
}

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

    // CREATE
    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BreachRule update(
            @PathVariable Long id,
            @RequestBody BreachRule rule
    ) {
        return breachRuleService.updateRule(id, rule);
    }

    // DEACTIVATE
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        breachRuleService.deactivateRule(id);
    }

    // GET ALL
    @GetMapping
    public List<BreachRule> getAll() {
        return breachRuleService.getAllRules();
    }

    // GET ACTIVE DEFAULT RULE
    @GetMapping("/active")
    public BreachRule getActiveDefault() {
        return breachRuleService.getActiveDefaultOrFirst();
    }
}

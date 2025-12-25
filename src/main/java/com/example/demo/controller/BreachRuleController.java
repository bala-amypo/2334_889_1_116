package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    private final BreachRuleService service;

    public BreachRuleController(BreachRuleService service) {
        this.service = service;
    }

    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/deactivate/{id}")
    public void deactivate(@PathVariable Long id) {
        service.deactivateRule(id);
    }

    @GetMapping
    public List<BreachRule> list() {
        return service.getAllRules();
    }
}

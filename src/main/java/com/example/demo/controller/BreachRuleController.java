package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breach-rules")
public class BreachRuleController {

    BreachRuleService breachRuleService;

    @PostMapping
    public BreachRule create(@RequestBody BreachRule rule) {
        return breachRuleService.createRule(rule);
    }

    @GetMapping
    public List<BreachRule> list() {
        return breachRuleService.getAllRules();
    }
}

package com.example.demo.controller;

import com.example.demo.entity.BreachRule;
import com.example.demo.service.BreachRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
@RequiredArgsConstructor
public class BreachRuleController {

    private final BreachRuleService breachRuleService;

    @PostMapping
    public BreachRule addRule(@RequestBody BreachRule rule) {
        return breachRuleService.save(rule);
    }

    @GetMapping
    public List<BreachRule> getAllRules() {
        return breachRuleService.findAll();
    }

    @GetMapping("/default")
    public BreachRule getDefaultRule() {
        return breachRuleService.findDefaultRule();
    }
}

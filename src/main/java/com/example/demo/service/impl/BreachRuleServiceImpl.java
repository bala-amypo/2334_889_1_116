package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    @Autowired   // 🔥 THIS FIXES THE 500 ERROR
    private BreachRuleRepository breachRuleRepository;

    // Needed for TestNG
    public BreachRuleServiceImpl() {
    }

    // Optional – Spring will ignore this now
    public BreachRuleServiceImpl(BreachRuleRepository breachRuleRepository) {
        this.breachRuleRepository = breachRuleRepository;
    }

    @Override
    public BreachRule createRule(BreachRule rule) {
        if (rule.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("penalty");
        }
        if (rule.getMaxPenaltyPercentage() < 0 || rule.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("percentage");
        }
        return breachRuleRepository.save(rule);
    }

    @Override
    public BreachRule updateRule(Long id, BreachRule rule) {
        BreachRule r = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        r.setPenaltyPerDay(rule.getPenaltyPerDay());
        r.setMaxPenaltyPercentage(rule.getMaxPenaltyPercentage());
        r.setActive(rule.getActive());
        r.setIsDefaultRule(rule.getIsDefaultRule());

        return breachRuleRepository.save(r);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule r = breachRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        r.setActive(false);
        breachRuleRepository.save(r);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active breach rule"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}

// package com.example.demo.service;

// import java.util.*;
// import org.springframework.stereotype.Service;
// import com.example.demo.entity.BreachRule;

// @Service
// public class BreachRuleServiceImpl implements BreachRuleService {

//     private final Map<Long, BreachRule> store = new HashMap<>();

//     @Override
//     public BreachRule save(BreachRule rule) {
//         store.put(rule.getId(), rule);
//         return rule;
//     }

//     @Override
//     public List<BreachRule> findAll() {
//         return new ArrayList<>(store.values());
//     }

//     @Override
//     public BreachRule findById(Long id) {
//         return store.get(id);
//     }

//     @Override
//     public BreachRule update(Long id, BreachRule rule) {
//         rule.setId(id);
//         store.put(id, rule);
//         return rule;
//     }

//     @Override
//     public void delete(Long id) {
//         store.remove(id);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.BreachRule;
import com.example.demo.repository.BreachRuleRepository;
import com.example.demo.service.BreachRuleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository breachRuleRepository;

    @Override
    public BreachRule createRule(BreachRule r) {
        if (r.getPenaltyPerDay().compareTo(BigDecimal.ZERO) <= 0 ||
                r.getMaxPenaltyPercentage() > 100) {
            throw new IllegalArgumentException("Invalid breach rule");
        }
        return breachRuleRepository.save(r);
    }

    @Override
    public void deactivateRule(Long id) {
        BreachRule r = breachRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        r.setActive(false);
        breachRuleRepository.save(r);
    }

    @Override
    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No active breach rule"));
    }

    @Override
    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}

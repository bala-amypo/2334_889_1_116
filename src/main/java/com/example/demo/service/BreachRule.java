package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BreachRule;

@Service
public class BreachRuleService {

    private final Map<Long, BreachRule> store = new HashMap<>();

    public BreachRule save(BreachRule rule) {
        store.put(rule.getId(), rule);
        return rule;
    }

    public List<BreachRule> findAll() {
        return new ArrayList<>(store.values());
    }

    public BreachRule findById(Long id) {
        return store.get(id);
    }

    public BreachRule update(Long id, BreachRule rule) {
        rule.setId(id);
        store.put(id, rule);
        return rule;
    }

    public void delete(Long id) {
        store.remove(id);
    }
}

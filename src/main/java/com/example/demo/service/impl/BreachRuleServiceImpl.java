package com.example.demo.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BreachRule;

@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private final Map<Long, BreachRule> store = new HashMap<>();

    @Override
    public BreachRule save(BreachRule rule) {
        store.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public List<BreachRule> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public BreachRule findById(Long id) {
        return store.get(id);
    }

    @Override
    public BreachRule update(Long id, BreachRule rule) {
        rule.setId(id);
        store.put(id, rule);
        return rule;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }
}

// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.BreachRule;

// public interface BreachRuleService {

//     BreachRule save(BreachRule rule);

//     List<BreachRule> findAll();

//     BreachRule findById(Long id);

//     BreachRule update(Long id, BreachRule rule);

//     void delete(Long id);
// }

package com.example.demo.service;

import com.example.demo.entity.BreachRule;

import java.util.List;

public interface BreachRuleService {

    BreachRule createRule(BreachRule rule);

    void deactivateRule(Long id);

    List<BreachRule> getAllRules();
}

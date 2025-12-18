package com.example.demo.repository;

import com.example.demo.entity.BreachRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    BreachRule findByRuleName(String ruleName);
}

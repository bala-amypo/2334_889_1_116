package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    Optional<BreachRule> findFirstByActiveTrueOrderByIsDefaultRuleDesc();
}

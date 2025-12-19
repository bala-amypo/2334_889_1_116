package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class BreachRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    private BigDecimal penaltyPerDay;

    private Double maxPenaltyPercentage;

    private Boolean active;

    public BreachRule() {}

    public BreachRule(String ruleName, BigDecimal penaltyPerDay,
                      Double maxPenaltyPercentage, Boolean active) {
        this.ruleName = ruleName;
        this.penaltyPerDay = penaltyPerDay;
        this.maxPenaltyPercentage = maxPenaltyPercentage;
        this.active = active;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }

    public BigDecimal getPenaltyPerDay() { return penaltyPerDay; }
    public void setPenaltyPerDay(BigDecimal penaltyPerDay) { this.penaltyPerDay = penaltyPerDay; }

    public Double getMaxPenaltyPercentage() { return maxPenaltyPercentage; }
    public void setMaxPenaltyPercentage(Double maxPenaltyPercentage) { this.maxPenaltyPercentage = maxPenaltyPercentage; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
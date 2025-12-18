package com.example.demo.entity;

import java.math.BigDecimal;

public class BreachRule {

    private Long id;
    private String ruleName;
    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private Boolean active;

    public BreachRule() {
    }

    public BreachRule(Long id, String ruleName, BigDecimal penaltyPerDay,
                      Double maxPenaltyPercentage, Boolean active) {
        this.id = id;
        this.ruleName = ruleName;
        this.penaltyPerDay = penaltyPerDay;
        this.maxPenaltyPercentage = maxPenaltyPercentage;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public BigDecimal getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public Double getMaxPenaltyPercentage() {
        return maxPenaltyPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }

    public void setMaxPenaltyPercentage(Double maxPenaltyPercentage) {
        this.maxPenaltyPercentage = maxPenaltyPercentage;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

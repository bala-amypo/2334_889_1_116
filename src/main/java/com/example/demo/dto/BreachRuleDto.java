package com.example.demo.dto;

import java.math.BigDecimal;

public class BreachRuleDto {

    private String ruleName;
    private BigDecimal penaltyPerDay;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public BigDecimal getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public void setPenaltyPerDay(BigDecimal penaltyPerDay) {
        this.penaltyPerDay = penaltyPerDay;
    }
}

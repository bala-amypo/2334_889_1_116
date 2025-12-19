package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;

    @ManyToOne
    private BreachRule appliedRule;

    public PenaltyCalculation() {
    }

    public PenaltyCalculation(Long id, Contract contract, Integer daysDelayed, BreachRule appliedRule) {
        this.id = id;
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.appliedRule = appliedRule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public BreachRule getAppliedRule() {
        return appliedRule;
    }

    public void setAppliedRule(BreachRule appliedRule) {
        this.appliedRule = appliedRule;
    }
}
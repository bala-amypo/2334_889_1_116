// package com.example.demo.entity;
package com.example.project.entity;

import jakarta.persistence.*;

@Entity
public class PenaltyCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    private Integer daysDelayed;

    @ManyToOne
    @JoinColumn(name = "breach_rule_id")
    private BreachRule appliedRule;

    public PenaltyCalculation() {
    }

    public PenaltyCalculation(Long id, Contract contract, Integer daysDelayed, BreachRule appliedRule) {
        this.id = id;
        this.contract = contract;
        this.daysDelayed = daysDelayed;
        this.appliedRule = appliedRule;
    }

    // Getters and Setters
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

package com.example.demo.dto;

import java.math.BigDecimal;

public class PenaltyCalculationDto {

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;

    public PenaltyCalculationDto() {}

    public Integer getDaysDelayed() {
        return daysDelayed;
    }

    public void setDaysDelayed(Integer daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public BigDecimal getCalculatedPenalty() {
        return calculatedPenalty;
    }

    public void setCalculatedPenalty(BigDecimal calculatedPenalty) {
        this.calculatedPenalty = calculatedPenalty;
    }
}

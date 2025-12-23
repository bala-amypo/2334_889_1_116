// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class BreachReport {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private int daysDelayed;

//     private String remark;

//     public BreachReport() {}

//     public BreachReport(int daysDelayed, String remark) {
//         this.daysDelayed = daysDelayed;
//         this.remark = remark;
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public int getDaysDelayed() { return daysDelayed; }
//     public void setDaysDelayed(int daysDelayed) { this.daysDelayed = daysDelayed; }

//     public String getRemark() { return remark; }
//     public void setRemark(String remark) { this.remark = remark; }
// }
package com.example.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "breach_reports")
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal penaltyAmount;
    private LocalDateTime generatedAt = LocalDateTime.now();

    public BreachReport() {}

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

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}

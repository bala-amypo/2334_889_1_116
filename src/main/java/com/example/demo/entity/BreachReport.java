package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class BreachReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int daysDelayed;

    private String remark;

    public BreachReport() {}

    public BreachReport(int daysDelayed, String remark) {
        this.daysDelayed = daysDelayed;
        this.remark = remark;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getDaysDelayed() { return daysDelayed; }
    public void setDaysDelayed(int daysDelayed) { this.daysDelayed = daysDelayed; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
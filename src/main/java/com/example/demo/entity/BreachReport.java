package com.example.demo.entity;

public class BreachReport {

    private Long id;
    private int daysDelayed;
    private String remark;

    public BreachReport() {
    }

    public BreachReport(Long id, int daysDelayed, String remark) {
        this.id = id;
        this.daysDelayed = daysDelayed;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public int getDaysDelayed() {
        return daysDelayed;
    }

    public String getRemark() {
        return remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDaysDelayed(int daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

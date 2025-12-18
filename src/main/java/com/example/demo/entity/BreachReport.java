package com.example.demo.entity;

public class BreacchReport{

    private Long id;
    private int daysDelayed;
    // private BigDecimal penaltyAmount;
    private String remark;

    public BreachReport(){
    }
    public BreachReport(Long id,int daysDelayed,String remark){
        this.id=id;
        this.daysDelayed=daysDelayed;
        this.remark=remark;
    }
    public Long getId() {
        return id;
    }

    public int getDaysDelayed() {
        return daysDelayed;
    }

    public String remark() {
        return remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDaysDelayed(String daysDelayed) {
        this.daysDelayed = daysDelayed;
    }

    public void remark(String remark) {
        this.remark = remark;
    }
}
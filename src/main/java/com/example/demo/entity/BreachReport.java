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
        
    }

}
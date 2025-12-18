package com.example.project.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class DeliveryRecord {
    private Long id;
    private Contract contract;
    private Date deliveryDate;
    private String notes;
    private Date createdAt;

    public DeliveryRecord() {
    }

    public DeliveryRecord(Long id, Contract contract, Date deliveryDate) {
        this.id = id;
        this.contract = contract;
        this.deliveryDate = deliveryDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}

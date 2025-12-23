// package com.example.demo.entity;

// import jakarta.persistence.*;
// import java.util.Date;

// @Entity
// public class DeliveryRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Contract contract;

//     @Temporal(TemporalType.DATE)
//     private Date deliveryDate;

//     private String notes;

//     @Temporal(TemporalType.TIMESTAMP)
//     private Date createdAt;

//     public DeliveryRecord() {
//     }

//     public DeliveryRecord(Long id, Contract contract, Date deliveryDate) {
//         this.id = id;
//         this.contract = contract;
//         this.deliveryDate = deliveryDate;
//     }

//     @PrePersist
//     protected void onCreate() {
//         this.createdAt = new Date();
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Contract getContract() { return contract; }
//     public void setContract(Contract contract) { this.contract = contract; }

//     public Date getDeliveryDate() { return deliveryDate; }
//     public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }

//     public String getNotes() { return notes; }
//     public void setNotes(String notes) { this.notes = notes; }

//     public Date getCreatedAt() { return createdAt; }
// }

package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "delivery_records")
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deliveryDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    public DeliveryRecord() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}

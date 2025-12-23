// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// public class Contract {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     @Column(unique = true)
//     private String contractNumber;
//     private String title;

//     public Contract() {
//     }

//     public Contract(Long id, String contractNumber, String title) {
//         this.id = id;
//         this.contractNumber = contractNumber;
//         this.title = title;
//     }

//     public Long getId() {
//         return id;
//     }

//     public String getContractNumber() {
//         return contractNumber;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public void setContractNumber(String contractNumber) {
//         this.contractNumber = contractNumber;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String contractNumber;

    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Contract() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCounterpartyName() {
        return counterpartyName;
    }

    public void setCounterpartyName(String counterpartyName) {
        this.counterpartyName = counterpartyName;
    }

    public LocalDate getAgreedDeliveryDate() {
        return agreedDeliveryDate;
    }

    public void setAgreedDeliveryDate(LocalDate agreedDeliveryDate) {
        this.agreedDeliveryDate = agreedDeliveryDate;
    }

    public BigDecimal getBaseContractValue() {
        return baseContractValue;
    }

    public void setBaseContractValue(BigDecimal baseContractValue) {
        this.baseContractValue = baseContractValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

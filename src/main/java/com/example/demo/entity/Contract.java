package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String contractNumber;
    private String title;

    public Contract() {
    }

    public Contract(Long id, String contractNumber, String title) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

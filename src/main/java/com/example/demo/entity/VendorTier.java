package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vendor_tier")
public class VendorTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tierName;

    private Integer minScoreThreshold;

    private Boolean active;

    public VendorTier() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public Integer getMinScoreThreshold() {
        return minScoreThreshold;
    }

    public void setMinScoreThreshold(Integer minScoreThreshold) {
        this.minScoreThreshold = minScoreThreshold;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

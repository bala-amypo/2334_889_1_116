package com.example.demo.repository;

 @Repository annotation.

public interface VendorTierRepository {

    boolean existsByTierName(String tierName);
}

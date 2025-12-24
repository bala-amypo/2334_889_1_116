package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.time.LocalDate;
import java.util.*;

public class DeliveryRecordServiceImpl {

    DeliveryRecordRepository deliveryRecordRepository;
    ContractRepository contractRepository;

    public DeliveryRecord createDeliveryRecord(DeliveryRecord r) {
        if (r.getDeliveryDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("in the future");

        contractRepository.findById(r.getContract().getId())
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        return deliveryRecordRepository.save(r);
    }

    public DeliveryRecord getLatestDeliveryRecord(Long id) {
        return deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id)
                .orElseThrow(() -> new RuntimeException("No delivery records found"));
    }

    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery record not found"));
    }

    public List<DeliveryRecord> getDeliveryRecordsForContract(Long id) {
        return deliveryRecordRepository.findByContractIdOrderByDeliveryDateAsc(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    @Autowired
    private DeliveryRecordRepository deliveryRecordRepository;

    @Autowired
    private ContractRepository contractRepository;

    // Required for TestNG & reflection-based tests
    public DeliveryRecordServiceImpl() {
    }

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord record) {

        if (record == null) {
            throw new IllegalArgumentException("Delivery record is null");
        }

        if (record.getDeliveryDate() == null) {
            throw new IllegalArgumentException("Delivery date is required");
        }

        if (record.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Delivery date cannot be in the future");
        }

        // 🔥 CORE FIX: prevent detached entity issue
        if (record.getContract() == null || record.getContract().getId() == null) {
            throw new IllegalArgumentException("Contract ID is required");
        }

        Long contractId = record.getContract().getId();

        Contract managedContract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        // Replace detached contract with managed entity
        record.setContract(managedContract);

        return deliveryRecordRepository.save(record);
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery record not found"));
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRecordRepository.findByContractIdOrderByDeliveryDateAsc(contractId);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("No delivery records found"));
    }
}

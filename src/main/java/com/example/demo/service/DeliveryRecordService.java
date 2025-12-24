package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;

import java.util.List;

public interface DeliveryRecordService {
    DeliveryRecord createDeliveryRecord(DeliveryRecord r);
    DeliveryRecord getLatestDeliveryRecord(Long contractId);
    DeliveryRecord getRecordById(Long id);
    List<DeliveryRecord> getDeliveryRecordsForContract(Long id);
}

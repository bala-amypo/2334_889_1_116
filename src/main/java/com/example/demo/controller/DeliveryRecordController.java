package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-records")
public class DeliveryRecordController {

    DeliveryRecordService deliveryRecordService;

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord record) {
        return deliveryRecordService.createDeliveryRecord(record);
    }

    @GetMapping("/{id}")
    public DeliveryRecord get(@PathVariable Long id) {
        return deliveryRecordService.getRecordById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> list(@PathVariable Long contractId) {
        return deliveryRecordService.getDeliveryRecordsForContract(contractId);
    }
}

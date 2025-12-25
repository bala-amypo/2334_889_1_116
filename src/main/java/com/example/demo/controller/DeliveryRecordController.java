package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord record) {
        return service.createDeliveryRecord(record);
    }

    @GetMapping("/latest/{contractId}")
    public DeliveryRecord latest(@PathVariable Long contractId) {
        return service.getLatestDeliveryRecord(contractId);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> list(@PathVariable Long contractId) {
        return service.getDeliveryRecordsForContract(contractId);
    }
}

package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    @PostMapping
    public DeliveryRecord addDelivery(@RequestBody DeliveryRecord record) {
        return deliveryRecordService.save(record);
    }

    @GetMapping("/latest/{contractId}")
    public DeliveryRecord getLatest(@PathVariable Long contractId) {
        return deliveryRecordService.findLatestByContractId(contractId);
    }
}

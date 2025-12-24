package com.example.demo.service;

import com.example.demo.entity.BreachRecord;

public interface PenaltyService {

    BreachRecord calculatePenalty(Long contractId, Long deliveryRecordId);
}

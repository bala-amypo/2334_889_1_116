package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordService {

    DeliveryRecord save(DeliveryRecord deliveryRecord);

    List<DeliveryRecord> findAll();

    DeliveryRecord findById(Long id);

    DeliveryRecord update(Long id, DeliveryRecord deliveryRecord);

    void delete(Long id);
}

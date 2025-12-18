package com.example.project.service;

import com.example.project.entity.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordService {

    DeliveryRecord save(DeliveryRecord deliveryRecord);

    List<DeliveryRecord> findAll();

    DeliveryRecord findById(Long id);

    DeliveryRecord update(Long id, DeliveryRecord deliveryRecord);

    void delete(Long id);
}

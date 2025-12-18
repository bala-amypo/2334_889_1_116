package com.example.demo.service;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repository;

    @Autowired
    public DeliveryRecordServiceImpl(DeliveryRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeliveryRecord save(DeliveryRecord deliveryRecord) {
        return repository.save(deliveryRecord);
    }

    @Override
    public List<DeliveryRecord> findAll() {
        return repository.findAll();
    }

    @Override
    public DeliveryRecord findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DeliveryRecord not found with id " + id));
    }

    @Override
    public DeliveryRecord update(Long id, DeliveryRecord deliveryRecord) {
        DeliveryRecord existing = findById(id);
        existing.setContract(deliveryRecord.getContract());
        existing.setDeliveryDate(deliveryRecord.getDeliveryDate());
        existing.setNotes(deliveryRecord.getNotes());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

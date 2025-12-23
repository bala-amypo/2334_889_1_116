// package com.example.demo.service;

// import com.example.demo.entity.DeliveryRecord;
// import com.example.demo.repository.DeliveryRecordRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class DeliveryRecordServiceImpl implements DeliveryRecordService {

//     private final DeliveryRecordRepository repository;

//     @Autowired
//     public DeliveryRecordServiceImpl(DeliveryRecordRepository repository) {
//         this.repository = repository;
//     }

//     @Override
//     public DeliveryRecord save(DeliveryRecord deliveryRecord) {
//         return repository.save(deliveryRecord);
//     }

//     @Override
//     public List<DeliveryRecord> findAll() {
//         return repository.findAll();
//     }

//     @Override
//     public DeliveryRecord findById(Long id) {
//         return repository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("DeliveryRecord not found with id " + id));
//     }

//     @Override
//     public DeliveryRecord update(Long id, DeliveryRecord deliveryRecord) {
//         DeliveryRecord existing = findById(id);
//         existing.setContract(deliveryRecord.getContract());
//         existing.setDeliveryDate(deliveryRecord.getDeliveryDate());
//         existing.setNotes(deliveryRecord.getNotes());
//         return repository.save(existing);
//     }

//     @Override
//     public void delete(Long id) {
//         repository.deleteById(id);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private DeliveryRecordRepository deliveryRecordRepository;
    private ContractRepository contractRepository;

    @Override
    public DeliveryRecord createDeliveryRecord(DeliveryRecord r) {
        if (r.getDeliveryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Delivery date cannot be in the future");
        }

        contractRepository.findById(r.getContract().getId())
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        return deliveryRecordRepository.save(r);
    }

    @Override
    public DeliveryRecord getLatestDeliveryRecord(Long contractId) {
        return deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(contractId)
                .orElseThrow(() -> new RuntimeException("No delivery records found"));
    }

    @Override
    public DeliveryRecord getRecordById(Long id) {
        return deliveryRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery record not found"));
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecordsForContract(Long contractId) {
        return deliveryRecordRepository.findByContractIdOrderByDeliveryDateAsc(contractId);
    }
}

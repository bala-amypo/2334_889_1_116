package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl(ContractRepository contractRepository, DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;

    }

    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be > 0");
        }
        return contractRepository.save(contract);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public Contract updateContract(Long id, Contract updated) {
        Contract existing = getContractById(id);
        existing.setTitle(updated.getTitle());
        existing.setCounterpartyName(updated.getCounterpartyName());
        existing.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        existing.setBaseContractValue(updated.getBaseContractValue());
        return contractRepository.save(existing);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);
        DeliveryRecord latest = deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id)
                .orElse(null);
        if (latest == null) {
            c.setStatus("ACTIVE");
        } else if (latest.getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
            c.setStatus("BREACHED");
        } else {
            c.setStatus("ACTIVE");
        }
        contractRepository.save(c);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    public Contract createContract(Contract c) {
        if (c.getBaseContractValue().intValue() <= 0)
            throw new IllegalArgumentException("Base contract value");
        return contractRepository.save(c);
    }

    public Contract updateContract(Long id, Contract c) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        existing.setTitle(c.getTitle());
        existing.setCounterpartyName(c.getCounterpartyName());
        existing.setAgreedDeliveryDate(c.getAgreedDeliveryDate());
        existing.setBaseContractValue(c.getBaseContractValue());
        return contractRepository.save(existing);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);
        deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id)
                .ifPresent(dr -> {
                    if (dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate()))
                        c.setStatus("BREACHED");
                });
        contractRepository.save(c);
    }
}

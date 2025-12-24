package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.*;
import java.time.LocalDate;

public class ContractServiceImpl {

    ContractRepository contractRepository;
    DeliveryRecordRepository deliveryRecordRepository;

    public Contract createContract(Contract c) {
        if (c.getBaseContractValue() == null || c.getBaseContractValue().signum() <= 0)
            throw new IllegalArgumentException("Base contract value");

        return contractRepository.save(c);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public Contract updateContract(Long id, Contract in) {
        Contract c = getContractById(id);
        c.setTitle(in.getTitle());
        c.setCounterpartyName(in.getCounterpartyName());
        c.setAgreedDeliveryDate(in.getAgreedDeliveryDate());
        c.setBaseContractValue(in.getBaseContractValue());
        return contractRepository.save(c);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);
        deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id)
                .ifPresentOrElse(
                    dr -> {
                        if (dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate()))
                            c.setStatus("BREACHED");
                    },
                    () -> c.setStatus("ACTIVE")
                );
        contractRepository.save(c);
    }
}

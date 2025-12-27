package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// public class ContractServiceImpl implements ContractService {

//     ContractRepository contractRepository;
//     DeliveryRecordRepository deliveryRecordRepository;

//     @Override
//     public Contract createContract(Contract contract) {
//         if (contract.getBaseContractValue() == null ||
//             contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
//             throw new IllegalArgumentException("Base contract value");
//         }
//         return contractRepository.save(contract);
//     }

//     @Override
//     public Contract updateContract(Long id, Contract data) {
//         Contract c = contractRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

//         c.setTitle(data.getTitle());
//         c.setCounterpartyName(data.getCounterpartyName());
//         c.setAgreedDeliveryDate(data.getAgreedDeliveryDate());
//         c.setBaseContractValue(data.getBaseContractValue());
//         return contractRepository.save(c);
//     }

//     @Override
//     public Contract getContractById(Long id) {
//         return contractRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
//     }

//     @Override
//     public List<Contract> getAllContracts() {
//         return contractRepository.findAll();
//     }

//     @Override
//     public void updateContractStatus(Long id) {
//         Contract c = contractRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

//         DeliveryRecord dr = deliveryRecordRepository
//                 .findFirstByContractIdOrderByDeliveryDateDesc(id)
//                 .orElse(null);

//         if (dr != null && dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
//             c.setStatus("BREACHED");
//         } else {
//             c.setStatus("ACTIVE");
//         }
//         contractRepository.save(c);
//     }
// }


@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;

    public ContractServiceImpl(
            ContractRepository contractRepository,
            DeliveryRecordRepository deliveryRecordRepository) {
        this.contractRepository = contractRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
    }

    @Override
    public Contract createContract(Contract contract) {
        if (contract.getBaseContractValue() == null ||
            contract.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be greater than zero");
        }
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract data) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        c.setTitle(data.getTitle());
        c.setCounterpartyName(data.getCounterpartyName());
        c.setAgreedDeliveryDate(data.getAgreedDeliveryDate());
        c.setBaseContractValue(data.getBaseContractValue());

        return contractRepository.save(c);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord dr = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id)
                .orElse(null);

        if (dr != null && dr.getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
            c.setStatus("BREACHED");
        } else {
            c.setStatus("ACTIVE");
        }
    }
}

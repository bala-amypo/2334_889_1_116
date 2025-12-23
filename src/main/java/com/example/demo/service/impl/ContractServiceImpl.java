// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.entity.Contract;
// import com.example.demo.repository.ContractRepository;
// import com.example.demo.service.ContractService;

// @Service
// public class ContractServiceImpl implements ContractService {

//     @Autowired
//     private ContractRepository repository;

//     @Override
//     public Contract save(Contract contract) {
//         return repository.save(contract);
//     }

//     @Override
//     public List<Contract> findAll() {
//         return repository.findAll();
//     }

//     @Override
//     public Contract findById(Long id) {
//         return repository.findById(id).orElse(null);
//     }

//     @Override
//     public Contract update(Long id, Contract contract) {
//         contract.setId(id);
//         return repository.save(contract);
//     }

//     @Override
//     public void delete(Long id) {
//         repository.deleteById(id);
//     }
// }

package com.example.demo.service.impl;

import com.example.demo.entity.Contract;
import com.example.demo.entity.DeliveryRecord;
import com.example.demo.repository.ContractRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.service.ContractService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Contract createContract(Contract c) {
        if (c.getBaseContractValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Base contract value must be positive");
        }
        return contractRepository.save(c);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    @Override
    public Contract updateContract(Long id, Contract updated) {
        Contract c = getContractById(id);
        c.setTitle(updated.getTitle());
        c.setCounterpartyName(updated.getCounterpartyName());
        c.setAgreedDeliveryDate(updated.getAgreedDeliveryDate());
        c.setBaseContractValue(updated.getBaseContractValue());
        return contractRepository.save(c);
    }

    @Override
    public void updateContractStatus(Long id) {
        Contract c = getContractById(id);

        Optional<DeliveryRecord> dr =
                deliveryRecordRepository.findFirstByContractIdOrderByDeliveryDateDesc(id);

        if (dr.isPresent() &&
                dr.get().getDeliveryDate().isAfter(c.getAgreedDeliveryDate())) {
            c.setStatus("BREACHED");
        } else {
            c.setStatus("ACTIVE");
        }
        contractRepository.save(c);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
}

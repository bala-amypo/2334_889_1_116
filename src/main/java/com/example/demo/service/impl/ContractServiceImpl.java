package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contract;
import com.example.demo.repository.ContractRepository;
import com.example.demo.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository repository;

    @Override
    public Contract save(Contract contract) {
        return repository.save(contract);
    }

    @Override
    public List<Contract> findAll() {
        return repository.findAll();
    }

    @Override
    public Contract findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Contract update(Long id, Contract contract) {
        contract.setId(id);
        return repository.save(contract);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

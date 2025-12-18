package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Contract;

public interface ContractService {

    Contract save(Contract contract);

    List<Contract> findAll();

    Contract findById(Long id);

    Contract update(Long id, Contract contract);

    void delete(Long id);
}

// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.Contract;

// public interface ContractService {

//     Contract save(Contract contract);

//     List<Contract> findAll();

//     Contract findById(Long id);

//     Contract update(Long id, Contract contract);

//     void delete(Long id);
// }

package com.example.demo.service;

import com.example.demo.entity.Contract;

import java.util.List;

public interface ContractService {

    Contract createContract(Contract contract);

    Contract getContractById(Long id);

    Contract updateContract(Long id, Contract contract);

    List<Contract> getAllContracts();
}

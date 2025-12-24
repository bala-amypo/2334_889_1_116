package com.example.demo.service;

import com.example.demo.entity.*;
import java.util.*;

public interface ContractService {
    Contract createContract(Contract c);
    Contract updateContract(Long id, Contract c);
    Contract getContractById(Long id);
    List<Contract> getAllContracts();
    void updateContractStatus(Long id);
}

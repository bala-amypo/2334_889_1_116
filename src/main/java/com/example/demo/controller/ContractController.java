// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.entity.Contract;
// import com.example.demo.service.ContractService;

// @RestController
// @RequestMapping("/contract")
// public class ContractController {

//     @Autowired
//     private ContractService service;

//     @PostMapping("/post")
//     public Contract create(@RequestBody Contract contract) {
//         return service.save(contract);
//     }

//     @GetMapping("/get")
//     public List<Contract> getAll() {
//         return service.findAll();
//     }

//     @GetMapping("/get/{id}")
//     public Contract getById(@PathVariable Long id) {
//         return service.findById(id);
//     }

//     @PutMapping("/update/{id}")
//     public Contract update(
//             @PathVariable Long id,
//             @RequestBody Contract contract) {
//         return service.update(id, contract);
//     }

//     @DeleteMapping("/delete/{id}")
//     public String delete(@PathVariable Long id) {
//         service.delete(id);
//         return "Contract with ID " + id + " deleted successfully!";
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.Contract;
import com.example.demo.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public Contract create(@RequestBody Contract c) {
        return contractService.createContract(c);
    }

    @GetMapping("/{id}")
    public Contract getById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable Long id, @RequestBody Contract c) {
        return contractService.updateContract(id, c);
    }

    @GetMapping
    public List<Contract> getAll() {
        return contractService.getAllContracts();
    }
}

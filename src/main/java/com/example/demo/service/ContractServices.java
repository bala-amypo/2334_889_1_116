package com.example.project.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.project.entity.Contract;

@Service
public class ContractServices {

    private Map<Long, Contract> mp = new HashMap<>();

    public Contract saveData(Contract ct) {
        mp.put(ct.getId(), ct);
        return ct;
    }

    public List<Contract> getAllData() {
        return new ArrayList<>(mp.values());
    }

    public Contract getById(Long id) {
        return mp.get(id);
    }

    public Contract updateData(Long id, Contract data) {
        mp.put(id, data);
        return data;
    }

    public void deleteData(Long id) {
        mp.remove(id);
    }
}

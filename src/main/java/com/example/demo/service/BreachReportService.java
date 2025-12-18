package com.example.demo.service;

import java.util.*;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Contract;

@Service
public class BreachReportService {
    private Map<Long, BreachReport> mp = new HashMap<>();
    public BreachReport saveData(BreachReport br) {
        mp.put(br.getId(), br);
        return br;
    }

    public List<BreachReport> getAllData() {
        return new ArrayList<>(mp.values());
    }

    public BreachReport getById(Long id) {
        return mp.get(id);
    }

    public BreachReport updateData(Long id, BreachReport data) {
        mp.put(id, data);
        return data;
    }

    public void deleteData(Long id) {
        mp.remove(id);
    }
}
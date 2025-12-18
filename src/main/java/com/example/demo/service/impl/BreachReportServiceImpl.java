package com.example.demo.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;

@Service
public class BreachReportServiceImpl implements BreachReportService {

    private Map<Long, BreachReport> mp = new HashMap<>();

    @Override
    public BreachReport saveData(BreachReport br) {
        mp.put(br.getId(), br);
        return br;
    }

    @Override
    public List<BreachReport> getAllData() {
        return new ArrayList<>(mp.values());
    }

    @Override
    public BreachReport getById(Long id) {
        return mp.get(id);
    }

    @Override
    public BreachReport updateData(Long id, BreachReport br) {
        mp.put(id, br);
        return br;
    }

    @Override
    public void deleteData(Long id) {
        mp.remove(id);
    }
}

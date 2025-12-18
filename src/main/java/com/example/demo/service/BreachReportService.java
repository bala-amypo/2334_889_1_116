package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.BreachReport;

public interface BreachReportService {

    BreachReport saveData(BreachReport br);

    List<BreachReport> getAllData();

    BreachReport getById(Long id);

    BreachReport updateData(Long id, BreachReport br);

    void deleteData(Long id);
}

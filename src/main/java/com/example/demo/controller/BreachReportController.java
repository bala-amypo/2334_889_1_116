package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;

@RestController
@RequestMapping("/BreachReport")
public class BreachReportController {
    @Autowired
    private BreachReportService src;
    @postMapping("/post")
    public BreachReport postData(@RequestBody BreachReport br) {
        return src.saveData(br);
    }
    @GetMapping("/get")
    public List<BreachReport> getAllData() {
        return src.getAllData();
    }

    @GetMapping("/get/{id}")
    public BreachReport getById(@PathVariable Long id) {
        return src.getById(id);
    }

    @PutMapping("/update/{id}")
    public BreachReport updateData(@PathVariable Long id, @RequestBody Contract ct) {
        return src.updateData(id, ct);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable Long id) {
        src.deleteData(id);
        return "Contract with ID " + id + " deleted successfully!";
    }
}
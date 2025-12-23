// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.entity.BreachReport;
// import com.example.demo.service.BreachReportService;

// @RestController
// @RequestMapping("/breach-report")
// public class BreachReportController {

//     @Autowired
//     private BreachReportService service;

//     @PostMapping("/post")
//     public BreachReport postData(@RequestBody BreachReport br) {
//         return service.saveData(br);
//     }

//     @GetMapping("/get")
//     public List<BreachReport> getAllData() {
//         return service.getAllData();
//     }

//     @GetMapping("/get/{id}")
//     public BreachReport getById(@PathVariable Long id) {
//         return service.getById(id);
//     }

//     @PutMapping("/update/{id}")
//     public BreachReport updateData(
//             @PathVariable Long id,
//             @RequestBody BreachReport br) {
//         return service.updateData(id, br);
//     }

//     @DeleteMapping("/delete/{id}")
//     public String deleteData(@PathVariable Long id) {
//         service.deleteData(id);
//         return "BreachReport with ID " + id + " deleted successfully!";
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.BreachReport;
import com.example.demo.service.BreachReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breach-reports")
public class BreachReportController {

    private final BreachReportService service;

    public BreachReportController(BreachReportService service) {
        this.service = service;
    }

    @PostMapping("/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return service.generateReport(contractId);
    }

    @GetMapping("/contract/{contractId}")
    public List<BreachReport> getByContract(@PathVariable Long contractId) {
        return service.getReportsForContract(contractId);
    }

    @GetMapping
    public List<BreachReport> getAll() {
        return service.getAllReports();
    }
}

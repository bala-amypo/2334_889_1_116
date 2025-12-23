// package com.example.demo.service;

// import java.util.List;
// import com.example.demo.entity.BreachReport;

// public interface BreachReportService {

//     BreachReport saveData(BreachReport br);

//     List<BreachReport> getAllData();

//     BreachReport getById(Long id);

//     BreachReport updateData(Long id, BreachReport br);

//     void deleteData(Long id);
// }

package com.example.demo.service;

import com.example.demo.entity.BreachReport;

import java.util.List;

public interface BreachReportService {

    BreachReport generateReport(Long contractId);

    List<BreachReport> getReportsForContract(Long contractId);

    List<BreachReport> getAllReports();
}

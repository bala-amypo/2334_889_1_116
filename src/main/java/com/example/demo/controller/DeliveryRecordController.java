// package com.example.demo.controller;

// import com.example.demo.entity.DeliveryRecord;
// import com.example.demo.service.DeliveryRecordService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/delivery-records")
// public class DeliveryRecordController {

//     private final DeliveryRecordService service;

//     @Autowired
//     public DeliveryRecordController(DeliveryRecordService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public DeliveryRecord create(@RequestBody DeliveryRecord deliveryRecord) {
//         return service.save(deliveryRecord);
//     }

//     @GetMapping
//     public List<DeliveryRecord> getAll() {
//         return service.findAll();
//     }

//     @GetMapping("/{id}")
//     public DeliveryRecord getById(@PathVariable Long id) {
//         return service.findById(id);
//     }

//     @PutMapping("/{id}")
//     public DeliveryRecord update(@PathVariable Long id, @RequestBody DeliveryRecord deliveryRecord) {
//         return service.update(id, deliveryRecord);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         service.delete(id);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-records")
public class DeliveryRecordController {

    private final DeliveryRecordService service;

    public DeliveryRecordController(DeliveryRecordService service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryRecord create(@RequestBody DeliveryRecord r) {
        return service.createDeliveryRecord(r);
    }

    @GetMapping("/{id}")
    public DeliveryRecord getById(@PathVariable Long id) {
        return service.getRecordById(id);
    }

    @GetMapping("/contract/{contractId}")
    public List<DeliveryRecord> getByContract(@PathVariable Long contractId) {
        return service.getDeliveryRecordsForContract(contractId);
    }
}

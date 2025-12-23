// package com.example.demo.controller;

// import com.example.demo.entity.PenaltyCalculation;
// import com.example.demo.service.PenaltyCalculationService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/penalties")
// public class PenaltyCalculationController {

//     private final PenaltyCalculationService service;

//     @Autowired
//     public PenaltyCalculationController(PenaltyCalculationService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public PenaltyCalculation create(@RequestBody PenaltyCalculation penaltyCalculation) {
//         return service.save(penaltyCalculation);
//     }

//     @GetMapping
//     public List<PenaltyCalculation> getAll() {
//         return service.findAll();
//     }

//     @GetMapping("/{id}")
//     public PenaltyCalculation getById(@PathVariable Long id) {
//         return service.findById(id);
//     }

//     @PutMapping("/{id}")
//     public PenaltyCalculation update(@PathVariable Long id, @RequestBody PenaltyCalculation penaltyCalculation) {
//         return service.update(id, penaltyCalculation);
//     }

//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Long id) {
//         service.delete(id);
//     }
// }

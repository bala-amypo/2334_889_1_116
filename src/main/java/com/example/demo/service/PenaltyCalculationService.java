// package com.example.demo.service;

// import com.example.demo.entity.PenaltyCalculation;
// import java.util.List;

// public interface PenaltyCalculationService {

//     PenaltyCalculation save(PenaltyCalculation penaltyCalculation);

//     List<PenaltyCalculation> findAll();

//     PenaltyCalculation findById(Long id);

//     PenaltyCalculation update(Long id, PenaltyCalculation penaltyCalculation);

//     void delete(Long id);
// }

package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculation;

import java.util.List;

public interface PenaltyCalculationService {

    PenaltyCalculation calculatePenalty(Long contractId);

    List<PenaltyCalculation> getCalculationsForContract(Long contractId);
}

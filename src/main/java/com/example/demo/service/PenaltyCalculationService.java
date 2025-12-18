package com.example.project.service;

import com.example.demo.entity.PenaltyCalculation;
import java.util.List;

public interface PenaltyCalculationService {

    PenaltyCalculation save(PenaltyCalculation penaltyCalculation);

    List<PenaltyCalculation> findAll();

    PenaltyCalculation findById(Long id);

    PenaltyCalculation update(Long id, PenaltyCalculation penaltyCalculation);

    void delete(Long id);
}

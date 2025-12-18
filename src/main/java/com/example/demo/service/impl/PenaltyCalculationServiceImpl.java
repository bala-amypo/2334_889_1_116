package com.example.demo.service;

import com.example.demo.entity.PenaltyCalculation;
import com.example.demo.repository.PenaltyCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private final PenaltyCalculationRepository repository;

    @Autowired
    public PenaltyCalculationServiceImpl(PenaltyCalculationRepository repository) {
        this.repository = repository;
    }

    @Override
    public PenaltyCalculation save(PenaltyCalculation penaltyCalculation) {
        return repository.save(penaltyCalculation);
    }

    @Override
    public List<PenaltyCalculation> findAll() {
        return repository.findAll();
    }

    @Override
    public PenaltyCalculation findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PenaltyCalculation not found with id " + id));
    }

    @Override
    public PenaltyCalculation update(Long id, PenaltyCalculation penaltyCalculation) {
        PenaltyCalculation existing = findById(id);
        existing.setContract(penaltyCalculation.getContract());
        existing.setDaysDelayed(penaltyCalculation.getDaysDelayed());
        existing.setAppliedRule(penaltyCalculation.getAppliedRule());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

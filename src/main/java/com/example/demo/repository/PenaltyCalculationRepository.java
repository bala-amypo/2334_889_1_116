public interface PenaltyCalculationRepository extends JpaRepository<PenaltyCalculation, Long> {
    List<PenaltyCalculation> findByContractId(Long id);
    Optional<PenaltyCalculation> findTopByContractIdOrderByCalculatedAtDesc(Long id);
}

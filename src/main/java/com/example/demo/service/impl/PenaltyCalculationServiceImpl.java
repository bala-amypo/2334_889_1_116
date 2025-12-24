@Service
public class PenaltyCalculationServiceImpl implements PenaltyCalculationService {

    private ContractRepository contractRepository;
    private DeliveryRecordRepository deliveryRecordRepository;
    private BreachRuleRepository breachRuleRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculation calculatePenalty(Long id) {
        Contract c = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        DeliveryRecord dr = deliveryRecordRepository
                .findFirstByContractIdOrderByDeliveryDateDesc(id)
                .orElseThrow(() -> new RuntimeException("No delivery record"));

        BreachRule rule = breachRuleRepository
                .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow();

        long days = Math.max(0,
                dr.getDeliveryDate().toEpochDay() - c.getAgreedDeliveryDate().toEpochDay());

        double maxPenalty = c.getBaseContractValue().doubleValue()
                * rule.getMaxPenaltyPercentage() / 100;

        double penalty = Math.min(days * rule.getPenaltyPerDay().doubleValue(), maxPenalty);

        PenaltyCalculation pc = PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed((int) days)
                .calculatedPenalty(BigDecimal.valueOf(penalty))
                .build();

        return penaltyCalculationRepository.save(pc);
    }

    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    public List<PenaltyCalculation> getCalculationsForContract(Long id) {
        return penaltyCalculationRepository.findByContractId(id);
    }
}

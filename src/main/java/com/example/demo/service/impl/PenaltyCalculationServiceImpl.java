@Service
public class PenaltyCalculationServiceImpl {

    @Autowired ContractRepository contractRepository;
    @Autowired DeliveryRecordRepository deliveryRecordRepository;
    @Autowired BreachRuleRepository breachRuleRepository;
    @Autowired PenaltyCalculationRepository penaltyCalculationRepository;

    public PenaltyCalculation calculatePenalty(Long id) {
        Contract c = contractRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        DeliveryRecord dr = deliveryRecordRepository
            .findFirstByContractIdOrderByDeliveryDateDesc(id)
            .orElseThrow(() -> new ResourceNotFoundException("No delivery record"));

        BreachRule r = breachRuleRepository
            .findFirstByActiveTrueOrderByIsDefaultRuleDesc()
            .orElseThrow(() -> new ResourceNotFoundException("No rule"));

        long days = Math.max(0,
            ChronoUnit.DAYS.between(c.getAgreedDeliveryDate(), dr.getDeliveryDate()));

        BigDecimal penalty = r.getPenaltyPerDay().multiply(BigDecimal.valueOf(days));
        BigDecimal max = c.getBaseContractValue()
            .multiply(BigDecimal.valueOf(r.getMaxPenaltyPercentage() / 100));

        if (penalty.compareTo(max) > 0) penalty = max;

        return penaltyCalculationRepository.save(
            PenaltyCalculation.builder()
                .contract(c)
                .daysDelayed((int) days)
                .calculatedPenalty(penalty)
                .build()
        );
    }

    public PenaltyCalculation getCalculationById(Long id) {
        return penaltyCalculationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Calculation not found"));
    }

    public List<PenaltyCalculation> getCalculationsForContract(Long id) {
        return penaltyCalculationRepository.findByContractId(id);
    }
}

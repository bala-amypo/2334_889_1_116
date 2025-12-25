@Service
public class BreachReportServiceImpl {

    @Autowired BreachReportRepository breachReportRepository;
    @Autowired PenaltyCalculationRepository penaltyCalculationRepository;
    @Autowired ContractRepository contractRepository;

    public BreachReport generateReport(Long id) {
        contractRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        PenaltyCalculation calc =
            penaltyCalculationRepository.findTopByContractIdOrderByCalculatedAtDesc(id)
                .orElseThrow(() -> new ResourceNotFoundException("No penalty calculation"));

        return breachReportRepository.save(
            BreachReport.builder()
                .contract(calc.getContract())
                .daysDelayed(calc.getDaysDelayed())
                .penaltyAmount(calc.getCalculatedPenalty())
                .build()
        );
    }

    public List<BreachReport> getReportsForContract(Long id) {
        return breachReportRepository.findByContractId(id);
    }

    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }
}

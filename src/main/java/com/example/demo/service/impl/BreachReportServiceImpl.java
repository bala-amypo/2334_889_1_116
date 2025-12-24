@Service
public class BreachReportServiceImpl implements BreachReportService {

    private BreachReportRepository breachReportRepository;
    private PenaltyCalculationRepository penaltyCalculationRepository;
    private ContractRepository contractRepository;

    public BreachReport generateReport(Long id) {
        contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        PenaltyCalculation pc = penaltyCalculationRepository
                .findTopByContractIdOrderByCalculatedAtDesc(id)
                .orElseThrow(() -> new RuntimeException("No penalty calculation"));

        BreachReport r = BreachReport.builder()
                .contract(pc.getContract())
                .daysDelayed(pc.getDaysDelayed())
                .penaltyAmount(pc.getCalculatedPenalty())
                .build();

        return breachReportRepository.save(r);
    }

    public List<BreachReport> getAllReports() {
        return breachReportRepository.findAll();
    }

    public List<BreachReport> getReportsForContract(Long id) {
        return breachReportRepository.findByContractId(id);
    }
}

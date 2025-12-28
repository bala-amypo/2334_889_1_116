@RestController
@RequestMapping("/api/penalties")
public class PenaltyCalculationController {

    @Autowired
    private PenaltyCalculationService penaltyCalculationService;

    @PostMapping("/calculate/{contractId}")
    public PenaltyCalculation calculate(@PathVariable Long contractId) {
        return penaltyCalculationService.calculatePenalty(contractId);
    }

    @GetMapping("/contract/{contractId}")
    public List<PenaltyCalculation> list(@PathVariable Long contractId) {
        return penaltyCalculationService.getCalculationsForContract(contractId);
    }
}

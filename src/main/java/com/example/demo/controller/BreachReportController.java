@RestController
@RequestMapping("/api/reports")
public class BreachReportController {

    @Autowired
    private BreachReportService breachReportService;

    @PostMapping("/generate/{contractId}")
    public BreachReport generate(@PathVariable Long contractId) {
        return breachReportService.generateReport(contractId);
    }

    @GetMapping
    public List<BreachReport> list() {
        return breachReportService.getAllReports();
    }
}

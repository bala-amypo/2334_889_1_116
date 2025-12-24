@Service
public class BreachRuleServiceImpl implements BreachRuleService {

    private BreachRuleRepository breachRuleRepository;

    public BreachRule createRule(BreachRule r) {
        if (r.getPenaltyPerDay().intValue() <= 0 || r.getMaxPenaltyPercentage() > 100)
            throw new IllegalArgumentException();
        return breachRuleRepository.save(r);
    }

    public void deactivateRule(Long id) {
        BreachRule r = breachRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule not found"));
        r.setActive(false);
        breachRuleRepository.save(r);
    }

    public BreachRule getActiveDefaultOrFirst() {
        return breachRuleRepository.findFirstByActiveTrueOrderByIsDefaultRuleDesc()
                .orElseThrow(() -> new RuntimeException("No active breach rule"));
    }

    public List<BreachRule> getAllRules() {
        return breachRuleRepository.findAll();
    }
}

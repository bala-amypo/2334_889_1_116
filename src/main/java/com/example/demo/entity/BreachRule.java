@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class BreachRule {
    @Id @GeneratedValue
    private Long id;

    private String ruleName;
    private BigDecimal penaltyPerDay;
    private Double maxPenaltyPercentage;
    private boolean active;
    private boolean isDefaultRule;
}

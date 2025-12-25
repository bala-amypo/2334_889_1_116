@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PenaltyCalculation {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private Integer daysDelayed;
    private BigDecimal calculatedPenalty;

    private LocalDateTime calculatedAt = LocalDateTime.now();
}

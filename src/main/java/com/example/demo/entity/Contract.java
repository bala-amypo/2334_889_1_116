@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Contract {
    @Id @GeneratedValue
    private Long id;

    private String contractNumber;
    private String title;
    private String counterpartyName;
    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();
}

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class DeliveryRecord {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Contract contract;

    private LocalDate deliveryDate;
    private String notes;
}

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @ElementCollection
    private Set<String> roles;
}

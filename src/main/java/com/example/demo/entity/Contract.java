// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.LocalDateTime;

// @Entity
// @Getter @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Contract {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String contractNumber;
//     private String title;
//     private String counterpartyName;
//     private LocalDate agreedDeliveryDate;
//     private BigDecimal baseContractValue;
//     private String status = "ACTIVE";

//     private LocalDateTime createdAt;
//     private LocalDateTime updatedAt;
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(nullable = false)
    private String title;

    @Column(name = "counterparty_name", nullable = false)
    private String counterpartyName;

    @Column(name = "agreed_delivery_date", nullable = false)
    private LocalDate agreedDeliveryDate;

    @Column(name = "base_contract_value", nullable = false)
    private BigDecimal baseContractValue;

    @Builder.Default
    @Column(nullable = false)
    private String status = "ACTIVE";

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String contractNumber;

    @NotBlank
    private String title;

    @NotBlank
    private String counterpartyName;

    @NotNull
    private LocalDate agreedDeliveryDate;

    @NotNull
    private BigDecimal baseContractValue;

    private String status = "ACTIVE";

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

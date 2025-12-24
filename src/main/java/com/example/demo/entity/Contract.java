package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractNumber;
    private String title;
    private String counterpartyName;

    private LocalDate agreedDeliveryDate;
    private BigDecimal baseContractValue;
    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();
}

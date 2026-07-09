package com.zetheta.kycamlorchestration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "aml_screening")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmlScreening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;

    private Boolean sanctionsMatch;

    private Boolean pepMatch;

    private String riskLevel;

    private LocalDateTime screenedAt;
}
package com.zetheta.kycamlorchestration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "risk_scores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;

    private Double riskScore;

    private String riskLevel;

    private LocalDateTime calculatedAt;
}
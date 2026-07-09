package com.zetheta.kycamlorchestration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "case_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caseId;

    private String customerId;

    private String riskLevel;

    private String caseStatus;

    private String assignedTo;

    private LocalDateTime createdAt;
}
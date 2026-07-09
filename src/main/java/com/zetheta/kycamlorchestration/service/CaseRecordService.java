package com.zetheta.kycamlorchestration.service;

import com.zetheta.kycamlorchestration.dto.CaseRequest;
import com.zetheta.kycamlorchestration.dto.CaseResponse;
import com.zetheta.kycamlorchestration.entity.CaseRecord;
import com.zetheta.kycamlorchestration.entity.RiskScore;
import com.zetheta.kycamlorchestration.kafka.CaseEventProducer;
import com.zetheta.kycamlorchestration.repository.CaseRecordRepository;
import com.zetheta.kycamlorchestration.repository.RiskScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CaseRecordService {

    private final CaseRecordRepository caseRecordRepository;
    private final RiskScoreRepository riskScoreRepository;
    private final CaseEventProducer caseEventProducer;

    // ===========================
    // Create Case
    // ===========================
    public CaseResponse createCase(CaseRequest request) {

        RiskScore riskScore = riskScoreRepository
                .findTopByCustomerIdOrderByCalculatedAtDesc(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Risk Score Not Found"));

        CaseRecord caseRecord = CaseRecord.builder()
                .caseId("CASE-" + UUID.randomUUID().toString().substring(0, 8))
                .customerId(request.getCustomerId())
                .riskLevel(riskScore.getRiskLevel())
                .caseStatus("OPEN")
                .assignedTo("Compliance Officer")
                .createdAt(LocalDateTime.now())
                .build();

        caseRecordRepository.save(caseRecord);

        // Publish Kafka Event
        caseEventProducer.publishCaseCreated(
                "Case Created | " +
                "Case ID: " + caseRecord.getCaseId() +
                " | Customer ID: " + caseRecord.getCustomerId() +
                " | Risk Level: " + caseRecord.getRiskLevel() +
                " | Status: " + caseRecord.getCaseStatus()
        );

        return CaseResponse.builder()
                .caseId(caseRecord.getCaseId())
                .customerId(caseRecord.getCustomerId())
                .riskLevel(caseRecord.getRiskLevel())
                .caseStatus(caseRecord.getCaseStatus())
                .assignedTo(caseRecord.getAssignedTo())
                .build();
    }

    // ===========================
    // Get Case by Customer ID
    // ===========================
    public CaseRecord getCase(String customerId) {

        return caseRecordRepository
                .findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Case Not Found"));
    }

    // ===========================
    // Update Case Status
    // ===========================
    public CaseRecord updateCaseStatus(String caseId, String status) {

        CaseRecord caseRecord = caseRecordRepository
                .findByCaseId(caseId)
                .orElseThrow(() -> new RuntimeException("Case Not Found"));

        caseRecord.setCaseStatus(status);

        CaseRecord updatedCase = caseRecordRepository.save(caseRecord);

        // Publish Kafka Event
        caseEventProducer.publishCaseCreated(
                "Case Updated | " +
                "Case ID: " + updatedCase.getCaseId() +
                " | Status: " + updatedCase.getCaseStatus()
        );

        return updatedCase;
    }
}
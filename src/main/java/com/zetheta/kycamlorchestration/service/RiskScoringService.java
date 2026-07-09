package com.zetheta.kycamlorchestration.service;

import com.zetheta.kycamlorchestration.dto.RiskScoreRequest;
import com.zetheta.kycamlorchestration.dto.RiskScoreResponse;
import com.zetheta.kycamlorchestration.entity.AmlScreening;
import com.zetheta.kycamlorchestration.entity.Customer;
import com.zetheta.kycamlorchestration.entity.RiskScore;
import com.zetheta.kycamlorchestration.repository.AmlScreeningRepository;
import com.zetheta.kycamlorchestration.repository.CustomerRepository;
import com.zetheta.kycamlorchestration.repository.RiskScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RiskScoringService {

    private final RiskScoreRepository riskScoreRepository;
    private final CustomerRepository customerRepository;
    private final AmlScreeningRepository amlScreeningRepository;

    public RiskScoreResponse calculateRisk(RiskScoreRequest request) {

        Customer customer = customerRepository
                .findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        AmlScreening aml = amlScreeningRepository
                .findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("AML record not found"));

        double score = 0;

        // KYC Score
        if ("KYC_VERIFIED".equalsIgnoreCase(customer.getStatus())) {
            score += 10;
        } else {
            score += 40;
        }

        // AML Score
        if ("HIGH".equalsIgnoreCase(aml.getRiskLevel())) {
            score += 60;
        } else if ("MEDIUM".equalsIgnoreCase(aml.getRiskLevel())) {
            score += 35;
        } else {
            score += 10;
        }

        // Country Risk
        if ("Afghanistan".equalsIgnoreCase(request.getCountry())
                || "North Korea".equalsIgnoreCase(request.getCountry())) {
            score += 30;
        } else {
            score += 10;
        }

        String riskLevel;

        if (score >= 80) {
            riskLevel = "HIGH";
        } else if (score >= 50) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "LOW";
        }

        // Get latest RiskScore or create a new one
        RiskScore risk = riskScoreRepository
                .findTopByCustomerIdOrderByCalculatedAtDesc(request.getCustomerId())
                .orElse(new RiskScore());

        risk.setCustomerId(request.getCustomerId());
        risk.setRiskScore(score);
        risk.setRiskLevel(riskLevel);
        risk.setCalculatedAt(LocalDateTime.now());

        riskScoreRepository.save(risk);

        return RiskScoreResponse.builder()
                .customerId(request.getCustomerId())
                .riskScore(score)
                .riskLevel(riskLevel)
                .build();
    }
}
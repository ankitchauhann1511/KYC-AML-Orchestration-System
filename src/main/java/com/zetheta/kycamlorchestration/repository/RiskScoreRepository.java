package com.zetheta.kycamlorchestration.repository;

import com.zetheta.kycamlorchestration.entity.RiskScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RiskScoreRepository
        extends JpaRepository<RiskScore, Long> {

    Optional<RiskScore> findTopByCustomerIdOrderByCalculatedAtDesc(String customerId);

    long count();

    long countByRiskLevel(String riskLevel);

}
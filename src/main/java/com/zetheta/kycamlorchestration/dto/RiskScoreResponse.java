package com.zetheta.kycamlorchestration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiskScoreResponse {

    private String customerId;

    private Double riskScore;

    private String riskLevel;
}
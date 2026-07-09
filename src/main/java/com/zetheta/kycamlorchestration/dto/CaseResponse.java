package com.zetheta.kycamlorchestration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseResponse {

    private String caseId;

    private String customerId;

    private String riskLevel;

    private String caseStatus;

    private String assignedTo;
}
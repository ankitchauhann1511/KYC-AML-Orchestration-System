package com.zetheta.kycamlorchestration.dto;

import lombok.Data;

@Data
public class CaseRequest {

    private String customerId;

    private String riskLevel;
}
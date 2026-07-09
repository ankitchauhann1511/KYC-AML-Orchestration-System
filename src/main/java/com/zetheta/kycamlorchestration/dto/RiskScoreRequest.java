package com.zetheta.kycamlorchestration.dto;

import lombok.Data;

@Data
public class RiskScoreRequest {

    private String customerId;

    private String country;
}
package com.zetheta.kycamlorchestration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmlResponse {

    private String customerId;

    private boolean sanctionsMatch;

    private boolean pepMatch;

    private String riskLevel;
}
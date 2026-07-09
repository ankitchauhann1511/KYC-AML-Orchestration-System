package com.zetheta.kycamlorchestration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KycResponse {

    private String customerId;

    private String documentType;

    private String verificationStatus;

}
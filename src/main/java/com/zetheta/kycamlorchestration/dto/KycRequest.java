package com.zetheta.kycamlorchestration.dto;

import lombok.Data;

@Data
public class KycRequest {

    private String customerId;

    private String documentType;

    private String documentNumber;

}
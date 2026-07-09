package com.zetheta.kycamlorchestration.dto;

import lombok.Data;

@Data
public class AmlRequest {

    private String customerId;

    private String fullName;

    private String country;
}
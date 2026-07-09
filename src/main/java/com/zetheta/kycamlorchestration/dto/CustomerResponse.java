package com.zetheta.kycamlorchestration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private String customerId;
    private String fullName;
    private String status;
}
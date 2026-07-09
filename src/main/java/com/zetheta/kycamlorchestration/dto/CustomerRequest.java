package com.zetheta.kycamlorchestration.dto;

import lombok.Data;

@Data
public class CustomerRequest {

    private String fullName;
    private String email;
    private String phone;
    private String panNumber;
    private String aadhaarNumber;
}
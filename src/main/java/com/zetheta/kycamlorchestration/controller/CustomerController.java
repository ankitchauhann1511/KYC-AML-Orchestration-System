package com.zetheta.kycamlorchestration.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zetheta.kycamlorchestration.dto.CustomerRequest;
import com.zetheta.kycamlorchestration.dto.CustomerResponse;
import com.zetheta.kycamlorchestration.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/onboard")
    public CustomerResponse onboardCustomer(
            @RequestBody CustomerRequest request) {

        return customerService.onboardCustomer(request);
    }
}
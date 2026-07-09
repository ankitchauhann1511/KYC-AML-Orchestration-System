package com.zetheta.kycamlorchestration.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zetheta.kycamlorchestration.dto.CustomerRequest;
import com.zetheta.kycamlorchestration.dto.CustomerResponse;
import com.zetheta.kycamlorchestration.entity.Customer;
import com.zetheta.kycamlorchestration.kafka.CustomerEventProducer;
import com.zetheta.kycamlorchestration.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEventProducer producer;

    public CustomerResponse onboardCustomer(
            CustomerRequest request) {

        Customer customer = Customer.builder()
                .customerId(UUID.randomUUID().toString())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .panNumber(request.getPanNumber())
                .aadhaarNumber(request.getAadhaarNumber())
                .status("PENDING_KYC")
                .createdAt(LocalDateTime.now())
                .build();

        customerRepository.save(customer);

        producer.publishCustomerCreated(
                customer.getCustomerId());

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .fullName(customer.getFullName())
                .status(customer.getStatus())
                .build();
    }
}
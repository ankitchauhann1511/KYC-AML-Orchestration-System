package com.zetheta.kycamlorchestration.service;

import com.zetheta.kycamlorchestration.dto.KycRequest;
import com.zetheta.kycamlorchestration.dto.KycResponse;
import com.zetheta.kycamlorchestration.entity.Customer;
import com.zetheta.kycamlorchestration.entity.KycDocument;
import com.zetheta.kycamlorchestration.kafka.KycEventProducer;
import com.zetheta.kycamlorchestration.repository.CustomerRepository;
import com.zetheta.kycamlorchestration.repository.KycDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KycService {

    private final KycDocumentRepository kycDocumentRepository;
    private final CustomerRepository customerRepository;
    private final KycEventProducer producer;

    public KycResponse uploadDocument(KycRequest request) {

        Customer customer = customerRepository
                .findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        String status = validateDocument(
                request.getDocumentType(),
                request.getDocumentNumber());

        KycDocument document = KycDocument.builder()
                .customerId(request.getCustomerId())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .verificationStatus(status)
                .uploadedAt(LocalDateTime.now())
                .build();

        kycDocumentRepository.save(document);

        if ("VERIFIED".equalsIgnoreCase(status)) {

            customer.setStatus("KYC_VERIFIED");
            customerRepository.save(customer);

            producer.publishVerifiedCustomer(customer.getCustomerId());
        }

        return KycResponse.builder()
                .customerId(document.getCustomerId())
                .documentType(document.getDocumentType())
                .verificationStatus(document.getVerificationStatus())
                .build();
    }

    public String getKycStatus(String customerId) {

        Customer customer = customerRepository
                .findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return customer.getStatus();
    }

    private String validateDocument(String type, String number) {

        // PAN Validation
        if ("PAN".equalsIgnoreCase(type)) {

            if (number.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                return "VERIFIED";
            }
            return "REJECTED";
        }

        // Aadhaar Validation
        if ("AADHAAR".equalsIgnoreCase(type)
                || "ADHAAR".equalsIgnoreCase(type)) {

            if (number.matches("\\d{12}")) {
                return "VERIFIED";
            }
            return "REJECTED";
        }

        return "PENDING";
    }
}
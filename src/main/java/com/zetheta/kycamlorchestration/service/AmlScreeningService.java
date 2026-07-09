package com.zetheta.kycamlorchestration.service;

import com.zetheta.kycamlorchestration.dto.AmlRequest;
import com.zetheta.kycamlorchestration.dto.AmlResponse;
import com.zetheta.kycamlorchestration.entity.AmlScreening;
import com.zetheta.kycamlorchestration.kafka.AmlEventProducer;
import com.zetheta.kycamlorchestration.repository.AmlScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AmlScreeningService {

    private final AmlScreeningRepository repository;
    private final AmlEventProducer producer;
    
    
    // Sample Sanctions List
    private static final List<String> SANCTIONS_LIST = List.of(
            "Osama Bin Laden",
            "John Terror",
            "Global Criminal"
    );

    // Sample PEP List
    private static final List<String> PEP_LIST = List.of(
            "Prime Minister",
            "Finance Minister",
            "Chief Minister"
    );

    public AmlResponse screenCustomer(AmlRequest request) {

        boolean sanctionsMatch = SANCTIONS_LIST.stream()
                .anyMatch(name -> name.equalsIgnoreCase(request.getFullName()));

        boolean pepMatch = PEP_LIST.stream()
                .anyMatch(name -> name.equalsIgnoreCase(request.getFullName()));

        String riskLevel;

        if (sanctionsMatch) {
            riskLevel = "HIGH";
        } else if (pepMatch) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "LOW";
        }

        AmlScreening screening = AmlScreening.builder()
                .customerId(request.getCustomerId())
                .sanctionsMatch(sanctionsMatch)
                .pepMatch(pepMatch)
                .riskLevel(riskLevel)
                .screenedAt(LocalDateTime.now())
                .build();

        repository.save(screening);
        producer.publishAmlResult(
        		request.getCustomerId(),
        		riskLevel
        		);

        return AmlResponse.builder()
                .customerId(request.getCustomerId())
                .sanctionsMatch(sanctionsMatch)
                .pepMatch(pepMatch)
                .riskLevel(riskLevel)
                .build();
    }
}
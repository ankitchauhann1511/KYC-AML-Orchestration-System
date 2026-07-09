package com.zetheta.kycamlorchestration.controller;

import com.zetheta.kycamlorchestration.dto.RiskScoreRequest;
import com.zetheta.kycamlorchestration.dto.RiskScoreResponse;
import com.zetheta.kycamlorchestration.entity.RiskScore;
import com.zetheta.kycamlorchestration.repository.RiskScoreRepository;
import com.zetheta.kycamlorchestration.service.RiskScoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk")
@RequiredArgsConstructor
public class RiskController {

    private final RiskScoringService riskScoringService;
    private final RiskScoreRepository riskScoreRepository;

    @PostMapping("/calculate")
    public RiskScoreResponse calculateRisk(@RequestBody RiskScoreRequest request) {
        return riskScoringService.calculateRisk(request);
    }

    @GetMapping("/{customerId}")
    public RiskScore getRisk(@PathVariable String customerId) {

        return riskScoreRepository
                .findTopByCustomerIdOrderByCalculatedAtDesc(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Risk Score Not Found"));
    }
}
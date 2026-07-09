package com.zetheta.kycamlorchestration.controller;

import com.zetheta.kycamlorchestration.dto.KycRequest;
import com.zetheta.kycamlorchestration.dto.KycResponse;
import com.zetheta.kycamlorchestration.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
@RequiredArgsConstructor
public class KycController {

    private final KycService kycService;

    @PostMapping("/upload")
    public KycResponse uploadDocument(@RequestBody KycRequest request) {
        return kycService.uploadDocument(request);
    }

    @GetMapping("/status/{customerId}")
    public String getStatus(@PathVariable String customerId) {
        return kycService.getKycStatus(customerId);
    }
}
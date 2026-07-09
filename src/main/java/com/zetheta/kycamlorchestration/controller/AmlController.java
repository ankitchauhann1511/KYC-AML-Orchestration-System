package com.zetheta.kycamlorchestration.controller;

import com.zetheta.kycamlorchestration.dto.AmlRequest;
import com.zetheta.kycamlorchestration.dto.AmlResponse;
import com.zetheta.kycamlorchestration.service.AmlScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aml")
@RequiredArgsConstructor
public class AmlController {

    private final AmlScreeningService amlScreeningService;

    @PostMapping("/screen")
    public AmlResponse screenCustomer(@RequestBody AmlRequest request) {

        return amlScreeningService.screenCustomer(request);
    }
}
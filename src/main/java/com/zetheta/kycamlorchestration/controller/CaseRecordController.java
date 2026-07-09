package com.zetheta.kycamlorchestration.controller;

import com.zetheta.kycamlorchestration.dto.CaseRequest;
import com.zetheta.kycamlorchestration.dto.CaseResponse;
import com.zetheta.kycamlorchestration.entity.CaseRecord;
import com.zetheta.kycamlorchestration.service.CaseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class CaseRecordController {

    private final CaseRecordService caseRecordService;

    // Create Case
    @PostMapping("/create")
    public CaseResponse createCase(@RequestBody CaseRequest request) {

        return caseRecordService.createCase(request);
    }

    // Get Case
    @GetMapping("/{customerId}")
    public CaseRecord getCase(@PathVariable String customerId) {

        return caseRecordService.getCase(customerId);
    }

    // Update Case Status
    @PutMapping("/{caseId}/status")
    public CaseRecord updateStatus(
            @PathVariable String caseId,
            @RequestParam String status) {

        return caseRecordService.updateCaseStatus(caseId, status);
    }

}
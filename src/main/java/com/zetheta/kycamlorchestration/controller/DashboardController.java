package com.zetheta.kycamlorchestration.controller;

import com.zetheta.kycamlorchestration.dto.DashboardResponse;
import com.zetheta.kycamlorchestration.entity.*;
import com.zetheta.kycamlorchestration.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardResponse summary(){

        return dashboardService.getDashboardSummary();

    }

    @GetMapping("/customers")
    public List<Customer> customers(){

        return dashboardService.getCustomers();

    }

    @GetMapping("/kyc")
    public List<KycDocument> kyc(){

        return dashboardService.getKycDocuments();

    }

    @GetMapping("/aml")
    public List<AmlScreening> aml(){

        return dashboardService.getAmlRecords();

    }

    @GetMapping("/risk")
    public List<RiskScore> risk(){

        return dashboardService.getRiskScores();

    }

    @GetMapping("/cases")
    public List<CaseRecord> cases(){

        return dashboardService.getCaseRecords();

    }

}
package com.zetheta.kycamlorchestration.service;

import com.zetheta.kycamlorchestration.dto.DashboardResponse;
import com.zetheta.kycamlorchestration.entity.*;
import com.zetheta.kycamlorchestration.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CustomerRepository customerRepository;

    private final KycDocumentRepository kycDocumentRepository;

    private final AmlScreeningRepository amlScreeningRepository;

    private final RiskScoreRepository riskScoreRepository;

    private final CaseRecordRepository caseRecordRepository;

    public DashboardResponse getDashboardSummary(){

        return DashboardResponse.builder()

                .totalCustomers(customerRepository.count())

                .verifiedKyc(
                        kycDocumentRepository.countByVerificationStatus("VERIFIED"))

                .amlScreened(
                        amlScreeningRepository.count())

                .highRiskCustomers(
                        riskScoreRepository.countByRiskLevel("HIGH"))

                .highRiskCustomers(
                        riskScoreRepository.countByRiskLevel("MEDIUM"))

                .highRiskCustomers(
                        riskScoreRepository.countByRiskLevel("LOW"))

                .openCases(
                        caseRecordRepository.countByCaseStatus("OPEN"))

                .closedCases(
                        caseRecordRepository.countByCaseStatus("CLOSED"))

                .build();

    }

    public List<Customer> getCustomers(){

        return customerRepository.findAll();

    }

    public List<KycDocument> getKycDocuments(){

        return kycDocumentRepository.findAll();

    }

    public List<AmlScreening> getAmlRecords(){

        return amlScreeningRepository.findAll();

    }

    public List<RiskScore> getRiskScores(){

        return riskScoreRepository.findAll();

    }

    public List<CaseRecord> getCaseRecords(){

        return caseRecordRepository.findAll();

    }

}
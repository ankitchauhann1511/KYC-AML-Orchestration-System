package com.zetheta.kycamlorchestration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private Long totalCustomers;

    private Long verifiedKyc;

    private Long amlScreened;

    private Long highRiskCustomers;

    private Long openCases;

    private Long closedCases;
}
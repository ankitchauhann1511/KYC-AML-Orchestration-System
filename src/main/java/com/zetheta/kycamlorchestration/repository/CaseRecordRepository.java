package com.zetheta.kycamlorchestration.repository;

import com.zetheta.kycamlorchestration.entity.CaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaseRecordRepository
        extends JpaRepository<CaseRecord, Long> {

    Optional<CaseRecord> findByCustomerId(String customerId);

    Optional<CaseRecord> findByCaseId(String caseId);

    long count();

    long countByCaseStatus(String caseStatus);

}
package com.zetheta.kycamlorchestration.repository;

import com.zetheta.kycamlorchestration.entity.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<KycDocument, Long> {

    long count();

    long countByVerificationStatus(String verificationStatus);

}
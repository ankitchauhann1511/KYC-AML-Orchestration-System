package com.zetheta.kycamlorchestration.repository;

import com.zetheta.kycamlorchestration.entity.AmlScreening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AmlScreeningRepository
        extends JpaRepository<AmlScreening, Long> {

    Optional<AmlScreening> findByCustomerId(String customerId);

    long count();

}
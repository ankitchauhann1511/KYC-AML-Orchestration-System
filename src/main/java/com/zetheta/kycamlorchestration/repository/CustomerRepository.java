package com.zetheta.kycamlorchestration.repository;

import com.zetheta.kycamlorchestration.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByCustomerId(String customerId);
	
    long count();

}
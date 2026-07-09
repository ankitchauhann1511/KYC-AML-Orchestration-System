package com.zetheta.kycamlorchestration.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KycEventConsumer {

    @KafkaListener(
            topics = "kyc-verified-topic",
            groupId = "kyc-group"
    )
    public void consume(String customerId) {

        System.out.println("-------------------------------------");
        System.out.println("KYC VERIFIED");
        System.out.println("Customer ID : " + customerId);
        System.out.println("-------------------------------------");
    }
}
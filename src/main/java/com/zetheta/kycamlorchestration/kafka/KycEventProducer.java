package com.zetheta.kycamlorchestration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KycEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishVerifiedCustomer(String customerId) {

        kafkaTemplate.send(
                "kyc-verified-topic",
                customerId
        );
    }
}
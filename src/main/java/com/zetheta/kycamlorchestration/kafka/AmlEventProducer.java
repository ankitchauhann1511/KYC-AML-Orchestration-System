package com.zetheta.kycamlorchestration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmlEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishAmlResult(String customerId, String riskLevel) {

        String message = customerId + " | AML Risk : " + riskLevel;

        kafkaTemplate.send("aml-screening-topic", message);
    }
}
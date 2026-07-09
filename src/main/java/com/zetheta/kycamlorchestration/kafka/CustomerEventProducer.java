package com.zetheta.kycamlorchestration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEventProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void publishCustomerCreated(String customerId) {

        kafkaTemplate.send(
                "customer-onboard-topic",
                customerId
        );
    }
}
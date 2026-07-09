package com.zetheta.kycamlorchestration.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AmlEventConsumer {

    @KafkaListener(
            topics = "aml-screening-topic",
            groupId = "aml-group"
    )
    public void consume(String message) {

        System.out.println("==================================");
        System.out.println("AML EVENT RECEIVED");
        System.out.println(message);
        System.out.println("==================================");
    }
}
package com.zetheta.kycamlorchestration.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CaseEventConsumer {

    @KafkaListener(
            topics = "case-created-topic",
            groupId = "case-group")
    public void consume(String message) {

        System.out.println("================================");
        System.out.println("Case Event Received");
        System.out.println(message);
        System.out.println("================================");
    }
}
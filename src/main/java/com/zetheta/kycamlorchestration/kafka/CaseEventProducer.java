package com.zetheta.kycamlorchestration.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CaseEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "case-created-topic";

    public void publishCaseCreated(String message) {

        kafkaTemplate.send(TOPIC, message);

        System.out.println("================================");
        System.out.println("Case Event Published");
        System.out.println(message);
        System.out.println("================================");
    }
}
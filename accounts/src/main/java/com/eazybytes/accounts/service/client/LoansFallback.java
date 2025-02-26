package com.eazybytes.accounts.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eazybytes.accounts.dto.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "loans-fallback-topic";

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
        // Send fallback message to Kafka
        String message = String.format("Fallback triggered for mobile: %s, correlationId: %s", mobileNumber, correlationId);
        kafkaTemplate.send(TOPIC, message);
        return ResponseEntity.ok(new LoansDto());
    }
}

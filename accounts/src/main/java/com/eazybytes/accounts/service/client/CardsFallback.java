package com.eazybytes.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eazybytes.accounts.dto.CardsDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardsFallback implements CardsFeignClient {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CardsFallback(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "cards-fallback-topic";

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        String fallbackMessage = String.format("Cards service is down! CorrelationId: %s, MobileNumber: %s", 
            correlationId, mobileNumber);
        log.info("Cards Fallback: {}", fallbackMessage);
        
        kafkaTemplate.send(TOPIC, fallbackMessage);
        return null;
    }
}

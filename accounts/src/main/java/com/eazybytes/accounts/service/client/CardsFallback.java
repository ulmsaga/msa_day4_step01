package com.eazybytes.accounts.service.client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eazybytes.accounts.dto.CardsDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardsFallback implements CardsFeignClient {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "cards-fallback-topic";

    private final RabbitTemplate rabbitTemplate;

    public CardsFallback(KafkaTemplate<String, String> kafkaTemplate, RabbitTemplate rabbitTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        
        String fallbackMessage = String.format("Cards service is down! CorrelationId: %s, MobileNumber: %s", correlationId, mobileNumber);

        // KAFKA로 대체 로직을 보내는 코드
        // log.info("[KafkaTemplate] Cards Fallback Send: {}", fallbackMessage);
        // kafkaTemplate.send(TOPIC, fallbackMessage);

        // RabbitMQ로 대체 로직을 보내는 코드
        log.info("[RabbitTemplate] Loans Fallback Send: {}", fallbackMessage);
        rabbitTemplate.convertAndSend("loans-fallback-queue", fallbackMessage);
        
        return ResponseEntity.ok(new CardsDto());
    }
}

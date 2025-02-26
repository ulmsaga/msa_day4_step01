package com.eazybytes.loans.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FallbackMessageListener {

    @KafkaListener(topics = "loans-fallback-topic", groupId = "loans-group")
    public void listenFallbackMessages(String message) {
        log.info("Received fallback message: {}", message);
        // 여기에 fallback 처리 로직 구현
    }
}

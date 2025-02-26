package com.eazybytes.accounts.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CardsFallbackMessageListener {

    @KafkaListener(topics = "cards-fallback-topic", groupId = "accounts-group")
    public void listenCardsFailureMessages(String message) {
        log.info("Received Cards service fallback message in Accounts service: {}", message);
        // 여기서 Cards 서비스 장애 시의 대체 로직을 구현
        // 예: 기본 카드 정보 제공 또는 사용자에게 서비스 일시 중단 알림
    }
}

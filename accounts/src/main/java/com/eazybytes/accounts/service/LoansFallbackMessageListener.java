package com.eazybytes.accounts.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoansFallbackMessageListener {

    // @KafkaListener(topics = "loans-fallback-topic", groupId = "accounts-group")
    // public void listenLoansFailureMessages(String message) {
        // log.info("[KafkaListener] Loans Fallback Message :: {}", message);
        // 여기서 Loans 서비스 장애 시의 대체 로직을 구현
        // 예: 기본 대출 정보 제공 또는 사용자에게 서비스 일시 중단 알림
    // }

    @RabbitListener(queues = "loans-fallback-queue")
    public void handleLoansFallback(String message) {
        log.info("[RabbitListener] Loans Fallback Message :: {}", message);
        // 여기서 Loans 서비스 장애 시의 대체 로직을 구현
    }

}

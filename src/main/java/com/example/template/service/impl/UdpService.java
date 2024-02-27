package com.example.template.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UdpService {

    public final UnicastSendingMessageHandler sendingMessageHandler;

    public void sendMessage(String message) {
        log.info("发送UDP消息: {}", message);
        sendingMessageHandler.handleMessage(MessageBuilder.withPayload(message).build());
    }

}

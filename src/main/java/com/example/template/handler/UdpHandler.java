package com.example.template.handler;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 用于接受UDP包
 */
@SuppressWarnings("unused")
@Slf4j
@Component
@Primary
public class UdpHandler implements MessageHandler {
    @Override
    public void handleMessage(@NotNull Message<?> message) throws MessagingException {
        String payload = null;
        try {
            payload = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("接收UDP消息-payload:{}", payload);
    }
}

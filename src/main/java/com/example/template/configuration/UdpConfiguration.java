package com.example.template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.MessageHandler;

@Configuration
public class UdpConfiguration {

    public static final int port = 6060;

    @Bean
    public IntegrationFlow integrationFlow(@SuppressWarnings("all") MessageHandler messageHandler) {
        return IntegrationFlow
                .from(new UnicastReceivingChannelAdapter(port, true))
                .handle(messageHandler)
                .get();
    }

    @Bean
    public UnicastSendingMessageHandler unicastSendingMessageHandler() {
        return new UnicastSendingMessageHandler("localhost", port, true);
    }

}

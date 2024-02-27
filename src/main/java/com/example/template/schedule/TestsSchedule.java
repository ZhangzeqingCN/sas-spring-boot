package com.example.template.schedule;

import com.example.template.service.impl.UdpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Slf4j
@RequiredArgsConstructor
public class TestsSchedule {

    final UdpService udpService;

    @Scheduled(cron = "*/20 * * * * ?")
    void printNowDate() {
        udpService.sendMessage("Hello");
        log.info("固定定时任务执行:--->sendMessage");
    }

    @Scheduled(fixedRate = 5000)
    void greet() {
        log.info("固定定时任务执行:--->hello");
    }

}

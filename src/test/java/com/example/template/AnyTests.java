package com.example.template;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

@SuppressWarnings({"EmptyMethod", "unused"})
@Slf4j
public class AnyTests {
    @SuppressWarnings("EmptyMethod")
    @Test
    public void testDateFormat() {
        log.info("HELLO");
    }

    @Test
    public void testSemaphore() {
        ExecutorService service = null;
        try {
            final Semaphore semaphore = new Semaphore(10);
            service = Executors.newFixedThreadPool(20);
            for (int i = 0; i < 100; i++) {
                final int index = i;
                service.submit(() -> {
                    try {
                        semaphore.acquire();
                        log.info("task {}", index);
                        semaphore.release();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                });
            }
            boolean awaited = service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (service != null) {
                service.shutdownNow();
            }
        }
    }
}

package com.example.template.learn;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@SuppressWarnings("unused")
@Slf4j
public class ThreadPoolTests {
    @Test
    public void testBase() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 25, 10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(60),
                new ThreadFactory() {
                    public static final AtomicInteger i = new AtomicInteger(1);

                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("Thread " + i.getAndIncrement());
                        return t;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 100; i++) {

            int finalI = i;
            Runnable r = () -> {
                try {
                    Thread.sleep(500);
                    log.info("Task " + finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };

            try {
                executor.execute(r);
            } catch (RejectedExecutionException e) {
                log.error("Rejected execution");
            }

        }

        executor.shutdown();
    }

    @Test
    public void testExecuteAndSubmit() {
        ExecutorService singleThreadExecutor = null;
        try {
            singleThreadExecutor = Executors.newSingleThreadExecutor();

            singleThreadExecutor.execute(() -> log.info("Exec"));

            Future<?> submit1 = singleThreadExecutor.submit(() -> log.info("Future<?> submit(Runnable<?>)"));
            log.info(Objects.toString(submit1.get()));

            Future<?> submit2 = singleThreadExecutor.submit(() -> log.info("Future<T> submit(Runnable<?>, T)"), "任务完成");
            log.info(Objects.toString(submit2.get()));

            Future<Integer> submit3 = singleThreadExecutor.submit(() -> {
                log.info("Future<T> submit(Callable)");
                return 1;
            });
            log.info(Objects.toString(submit3.get()));


        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (singleThreadExecutor != null && !singleThreadExecutor.isShutdown()) {
                singleThreadExecutor.shutdown();
            }
        }
    }

    @Test
    public void testFutureGet() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // get()
        Callable<Integer> callable = () -> 1;
        Future<Integer> future = singleThreadExecutor.submit(callable);
        try {
            Integer i = future.get();
            log.info("get() {}", i);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
        // get(timeout)
        callable = () -> {
            TimeUnit.SECONDS.sleep(3);
            return 2;
        };
        future = singleThreadExecutor.submit(callable);
        try {
            Integer i = future.get(3, TimeUnit.SECONDS);
            log.info("get() {}", i);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        } catch (TimeoutException e) {
            log.info("Timeout");
        }

        singleThreadExecutor.shutdown();
    }

    @Test
    public void testFutureCancel() {
        Callable<Integer> callable = () -> 1;
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<Integer> future1 = singleThreadExecutor.submit(callable);
        Future<Integer> future2 = singleThreadExecutor.submit(callable);
        Future<Integer> future3 = singleThreadExecutor.submit(callable);

        log.info(Boolean.toString(future3.cancel(true)));


    }
}



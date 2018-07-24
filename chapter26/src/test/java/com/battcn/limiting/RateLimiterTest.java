package com.battcn.limiting;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 * @since 2018/7/24 0024
 */
public class RateLimiterTest {

    /**
     * 令牌桶算法
     * 每秒生成 2 個令牌
     */
    private static final RateLimiter limiter = RateLimiter.create(2);

    private void rateLimiter() {
        // 默认就是 1
        final double acquire = limiter.acquire(1);
        System.out.println("当前时间 - " + LocalDateTime.now() + " - " + Thread.currentThread().getName() + " - 阻塞 - " + acquire + " 通过...");
    }

    @Test
    public void testDemo1() throws InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(this::rateLimiter);
        }
        TimeUnit.SECONDS.sleep(5);
    }

}

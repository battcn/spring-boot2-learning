package com.battcn.limiting;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 * @since 2018/7/23 0023
 */
public class SemaphoreLimiterTest {


    /**
     * 计数器限流算法（允许将任务放入到缓冲队列）
     * 信号量，用来达到削峰的目的
     */
    private static final Semaphore semaphore = new Semaphore(3);


    private void semaphoreLimiter() {
        // 队列中允许存活的任务个数不能超过 5 个
        if (semaphore.getQueueLength() > 5) {
            System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " - 拒絕...");
        } else {
            try {
                semaphore.acquire();
                System.out.println(LocalDateTime.now() + " - " + Thread.currentThread().getName() + " - 通过...");
                //处理核心逻辑
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }


    @Test
    public void testSemaphore() throws InterruptedException {
        final ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(this::semaphoreLimiter);
        }
        TimeUnit.SECONDS.sleep(5);
    }
}

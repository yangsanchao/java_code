package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.*;


/**
 * <p>Description: ThreadPollDemo</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/20 15:22
 **/
@Log4j2
public class ThreadPollDemo {

    private static void work(Integer workId) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            Integer workId = i;
            threadPool.execute(() -> {
                log.info("threadPool {}", workId);
                ThreadPollDemo.work(workId);
            });
        }


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(() -> {
            log.info("scheduledExecutorService ");
        }, 1, TimeUnit.MILLISECONDS);
    }
}

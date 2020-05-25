package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>Description: ThreadDemo0</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/20 12:38
 **/
@Log4j2
public class ThreadDemo0 {
    public static void main(String[] args) {
        new Thread(() -> {
            log.info("1 :{}", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error(e);
            }
        }, "aaa").start();
        log.info("-11-");

        new Thread(() -> {
            log.info("2 :{}", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error(e);
            }
        }, "bbb").run();
        log.info("-22-");


        FutureTask futureTask = new FutureTask(new CallableTest());
        new Thread(futureTask,"cc").start();

        try {
            futureTask.get();
        } catch (InterruptedException e) {
            log.error(e);
        } catch (ExecutionException e) {
            log.error(e);
        }
    }

    static class CallableTest implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log.info("do task!");
            return 1;
        }
    }
}

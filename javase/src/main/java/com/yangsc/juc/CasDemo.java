package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description: CasDemo</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/5/26 11:39
 **/
@Log4j2
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                atomicInteger.incrementAndGet();
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                atomicInteger.incrementAndGet();
            }
        },"b").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(atomicInteger.get());
    }
}

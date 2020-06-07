package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Description: ReentrantLockTest</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/24 17:03
 **/
@Log4j2
public class ReentrantLockTest {

    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        int c = 2;
        a = b = c;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        try {
            log.info("lock");
        } catch (Exception e) {
            log.error(e);
        } finally {
            reentrantLock.unlock();
            log.info("unlock");
        }
    }
}

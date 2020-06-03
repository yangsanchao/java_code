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
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        try {
            log.info("lock");
        } catch (Exception e) {
            log.error(e);
        } finally {
            reentrantLock.unlock();
        }
    }
}

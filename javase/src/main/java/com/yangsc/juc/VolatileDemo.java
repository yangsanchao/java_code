package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

/**
 * <p>Description: VolatileDemo</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/5/25 21:56
 **/
@Log4j2
public class VolatileDemo {

    private static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            while (flag) {
            }
            log.info("here");
        }, "name").start();
        Thread.sleep(1000);
        flag = false;
    }
}

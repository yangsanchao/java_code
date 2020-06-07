package com.yangsc.juc;
/**
 * <p>Description: InterruptTest</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/6/4 14:34
 **/
public class InterruptTest {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // do more work.
                Boolean interrupted = Thread.interrupted();
                System.out.println("work: " + interrupted);
            }
            System.out.println("work end");
        });
        thread.start();
        System.out.println("wating!");
        // 一段时间以后
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;
import org.openjdk.jol.info.ClassLayout;

/**
 * <p>Description: ObjectLayout</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/5/27 00:49
 **/
@Log4j2
public class ObjectLayout1 {

    public static void main(String[] args) {
//        MyObj obj = new MyObj();
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
//        synchronized (obj) {
//            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
//        }
//        for (int i = 0; i < 100; i++) {
//            new Thread(()->{
//                obj.add();
//            }).start();
//        }
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        Long start = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10000000; i++) {
            stringBuffer.append("dd");
        }
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }
}

class MyObj {
    private Integer count = 0;
    public synchronized void add() {
        count++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

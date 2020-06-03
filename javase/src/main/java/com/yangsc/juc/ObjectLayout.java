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
public class ObjectLayout {

    public static synchronized void test(){

    }

    public synchronized void test1(){

    }

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj){
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        ObjectLayout.test();

        ObjectLayout objectLayout = new ObjectLayout();
        objectLayout.test1();
    }
}

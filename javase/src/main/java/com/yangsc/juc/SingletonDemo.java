package com.yangsc.juc;

import lombok.extern.log4j.Log4j2;

/**
 * <p>Description: SingletonDemo</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/5/27 13:27
 **/
@Log4j2
public class SingletonDemo {
    public static final SingletonDemo instance = new SingletonDemo();
    private SingletonDemo(){
    }
    public static SingletonDemo getInstance(){
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                SingletonDemo singletonDemo = SingletonDemo.getInstance();
                log.info(singletonDemo);
            }).start();
        }
    }
}

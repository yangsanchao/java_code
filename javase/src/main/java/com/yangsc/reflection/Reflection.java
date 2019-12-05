package com.yangsc.reflection;


import lombok.extern.log4j.Log4j2;

/**
 * <p>Description: Reflection</p>
 *
 * <p>Copyright: © 2018-至今 .All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2019-09-20 10:33:33
 **/
@Log4j2
public class Reflection {


    /**
     * class Reflection
     * @param obj obj
     */
    private void printObjectInfo(Object obj) {
        Class cls = obj.getClass();
        log.info(cls.getName());
        log.info(cls.getAnnotations());
        log.info(cls.getDeclaredAnnotations());
        log.info(cls.getCanonicalName());

    }

    private void classNameTest(){
        printObjectInfo(String.class);
        printObjectInfo(Integer.class);

    }

    /**
     * main
     * @param args args
     */
    public static void main(String[] args){
        Reflection reflection = new Reflection();
        reflection.classNameTest();
    }

}
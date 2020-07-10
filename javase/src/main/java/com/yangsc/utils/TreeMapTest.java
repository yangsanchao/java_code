package com.yangsc.utils;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: TreeMapTest</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/7/2 15:19
 **/
public class TreeMapTest {


    public static void main(String[] args) {
        TreeMap<Integer,Integer> map  = new TreeMap<>();
        map.put(2,2);
        map.put(1,1);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        System.out.println();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.get("a");

    }
}

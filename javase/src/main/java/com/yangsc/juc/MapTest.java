package com.yangsc.juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>Description: MapTest</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/6/25 20:55
 **/
public class MapTest {
    /**
     *
     */
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("key","value");

        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap(16);
        concurrentMap.put("key", "value");
        concurrentMap.size();


    }
}

package com.yangsc.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Description: LRU</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date  2020/7/10 15:50
 **/
public class LRU extends LinkedHashMap<Integer, Integer> {

    private Integer capacity;

    public LRU(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRU cache = new LRU(2);

        cache.put(1, 1);
        cache.put(2, 2);
        Integer v1 = cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        Integer v2 = cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        Integer v3 = cache.get(1);       // 返回 -1 (未找到)
        Integer v4 = cache.get(3);       // 返回  3
        Integer v5 = cache.get(4);       // 返回  4
        System.out.println();

    }
}

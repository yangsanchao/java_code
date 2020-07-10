package com.yangsc;

import com.yangsc.juc.MyLock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>Description: LRU</p>
 *
 * <p>Copyright: © 2018-至今 All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2020/7/10 15:50
 **/
public class LRUCache {

    class MyNode {
        int key;
        int value;
        MyNode prev;
        MyNode next;

        public MyNode() {
        }

        public MyNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    private int capacity;
    private int size;

    private Map<Integer, MyNode> cache = new HashMap<>();
    private MyNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new MyNode();
        this.tail = new MyNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        MyNode node = cache.get(key);
        if (node == null) {
            return -1;
        } else {
            // 更换到头节点
            moveToHead(node);
            return node.value;
        }
    }

    private void moveToHead(MyNode node) {
        removeNode(node);
        addToHead(node);
    }

    public void put(int key, int value) {
        MyNode node = cache.get(key);
        if (node == null) {
            node = new MyNode(key, value);
            addToHead(node);
            size++;
            if (size > capacity) {
                MyNode tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
            cache.put(key,node);
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(MyNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private MyNode removeTail() {
        MyNode node = tail.prev;
        removeNode(node);
        return node;
    }


    private void removeNode(MyNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);
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

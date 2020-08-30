package cn.edu.nju.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 460
 */
public class LFUCache implements Cache {

    private final int capacity;
    private int minFreq;
    private final Map<Integer, ListNode> nodeMap;
    private final Map<Integer, DoubleLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.nodeMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    @Override
    public int get(int key) {
        ListNode node = nodeMap.get(key);
        if (node == null)
            return -1;
        DoubleLinkedList list = freqMap.get(node.freq);
        list.remove(node);
        if (minFreq == node.freq && list.isEmpty())
            minFreq++;
        node.freq++;
        list = freqMap.getOrDefault(node.freq, new DoubleLinkedList());
        list.insertFirst(node);
        freqMap.putIfAbsent(node.freq, list);
        return node.value;
    }

    @Override
    public void put(int key, int value) {
        if (capacity == 0)
            return;
        ListNode node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            get(key);
            return;
        }
        node = new ListNode(key, value);
        nodeMap.put(key, node);
        if (nodeMap.size() > capacity) {
            ListNode last = freqMap.get(minFreq).removeLast();
            nodeMap.remove(last.key);
        }
        minFreq = 1;
        DoubleLinkedList list = freqMap.getOrDefault(1, new DoubleLinkedList());
        list.insertFirst(node);
        freqMap.putIfAbsent(1, list);
    }

    private static class ListNode {
        int key, value;
        int freq;
        ListNode prev, next;
        ListNode() {}
        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    private static class DoubleLinkedList {
        ListNode head, tail;

        DoubleLinkedList() {
            head = new ListNode();
            tail = new ListNode();
            head.next = tail;
            tail.prev = head;
        }

        boolean isEmpty() { return head.next == tail; }

        ListNode remove(ListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
            return node;
        }

        void insertFirst(ListNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        ListNode removeLast() {
            return remove(tail.prev);
        }
    }

    public static void main(String[] args) {
        // LeetCode Test case
        Cache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }

}

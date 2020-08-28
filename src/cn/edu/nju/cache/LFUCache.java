package cn.edu.nju.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 460
 */
public class LFUCache implements Cache {

    private final int capacity;
    private int minFreq;
    private final Map<Integer, ListNode> freqHeadMap;
    private final Map<Integer, ListNode> freqTailMap;
    private final Map<Integer, ListNode> nodeMap;

    private static class ListNode {
        int key, value;
        int freq;
        ListNode prev, next;
        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }

        @Override
        public String toString() {
            ListNode p = next;
            StringBuilder sb = new StringBuilder();
            sb.append('(').append(key).append(',').append(value).append(')');
            while (p != null) {
                sb.append("<->").append('(').append(p.key).append(',').append(p.value).append(')');
                p = p.next;
            }
            return sb.toString();
        }
    }

    private void remove(ListNode node) {
        int freq = node.freq;
        ListNode prev = node.prev;
        ListNode next = node.next;
        node.prev = null;
        node.next = null;
        if (prev == null && next == null) {
            freqHeadMap.remove(freq);
            freqTailMap.remove(freq);
            return;
        }
        if (prev == null)
            freqHeadMap.put(freq, next);
        else
            prev.next = next;
        if (next == null)
            freqTailMap.put(freq, prev);
        else
            next.prev = prev;
    }

    private void insertFirst(int freq, ListNode node) {
        ListNode head = freqHeadMap.get(freq);
        if (head != null) {
            node.next = head;
            head.prev = node;
        } else {
            freqTailMap.put(freq, node);
        }
        freqHeadMap.put(freq, node);
    }

    private ListNode removeLast(int freq) {
        ListNode tail = freqTailMap.get(freq);
        ListNode prev = tail.prev;
        if (prev == null) {
            freqHeadMap.remove(freq);
            freqTailMap.remove(freq);
        } else {
            prev.next = null;
            freqTailMap.put(freq, prev);
        }
        return tail;
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.freqHeadMap = new HashMap<>();
        this.freqTailMap = new HashMap<>();
        this.nodeMap = new HashMap<>();
    }

    @Override
    public int get(int key) {
        ListNode node = nodeMap.get(key);
        if (node == null)
            return -1;
        remove(node);
        if (minFreq == node.freq && !freqHeadMap.containsKey(node.freq))
            minFreq++;
        node.freq++;
        insertFirst(node.freq, node);
        return node.value;
    }

    @Override
    public void put(int key, int value) {
        ListNode node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            get(key);
            return;
        }
        if (capacity == 0)
            return;
        node = new ListNode(key, value);
        nodeMap.put(key, node);
        if (nodeMap.size() > capacity) {
            ListNode last = removeLast(minFreq);
            nodeMap.remove(last.key);
        }
        minFreq = 1;
        insertFirst(1, node);
    }
}

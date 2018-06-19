package cn.edu.nju.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 *
 * LeetCode 146
 *
 */
public class LRUCache {

    private final int capacity;
    private final Map<Integer, ListNode> nodeMap;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new ConcurrentHashMap<>();

        this.head = new ListNode();
        this.tail = new ListNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(Integer key) {
        int res = -1;
        if (nodeMap.containsKey(key)) {
            ListNode node = nodeMap.get(key);
            moveToFront(node);
            res = node.value;
        }
        return res;
    }

    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            ListNode node = nodeMap.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            if (nodeMap.size() == capacity) {
                ListNode removeNode = pop();
                nodeMap.remove(removeNode.key);
            }
            ListNode newNode = new ListNode(key, value);
            addToFront(newNode);
            nodeMap.put(key, newNode);
        }
    }

    /**
     * 双向链表节点
     */
    private static class ListNode {
        Integer key;
        Integer value;

        ListNode pre;
        ListNode next;

        ListNode() {}

        ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 删除双向链表中节点
     */
    private void removeNode(ListNode node) {
        ListNode pre = node.pre;
        ListNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    /**
     * 将双向链表中节点移动到最前端
     */
    private void moveToFront(ListNode node) {
        removeNode(node);
        addToFront(node);
    }

    /**
     * 在双向链表头部添加节点
     */
    private void addToFront(ListNode node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    /**
     * 删除双向链表尾部节点
     */
    private ListNode pop() {
        ListNode last = tail.pre;
        removeNode(last);
        return last;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

}

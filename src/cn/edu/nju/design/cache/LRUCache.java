package cn.edu.nju.design.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * LeetCode 146
 */
public class LRUCache implements Cache {

    private final int capacity;
    private final Map<Integer, ListNode> nodeMap;
    private final ListNode head;
    private final ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new ConcurrentHashMap<>();

        this.head = new ListNode();
        this.tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public int get(int key) {
        int res = -1;
        if (nodeMap.containsKey(key)) {
            ListNode node = nodeMap.get(key);
            moveToFront(node);
            res = node.value;
        }
        return res;
    }

    @Override
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
        int key, value;
        ListNode prev, next;
        ListNode() {}
        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 删除双向链表中节点
     */
    private void removeNode(ListNode node) {
        ListNode pre = node.prev;
        ListNode next = node.next;
        pre.next = next;
        next.prev = pre;
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
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    /**
     * 删除双向链表尾部节点
     */
    private ListNode pop() {
        ListNode last = tail.prev;
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

package cn.edu.nju.queue;

import cn.edu.nju.linkedlist.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 合并k个有序链表
 * 优先队列实现
 *
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        heap.addAll(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            p.next = new ListNode(node.val);
            p = p.next;
            if (node.next != null) {
                heap.add(node.next);
            }
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, 4, 23, 23);
        ListNode l2 = new ListNode(1, 6, 10, 19);
        ListNode l3 = new ListNode(7, 10, 22, 25);

        ListNode res = new MergeKSortedLists().mergeKLists(new ListNode[]{l1, l2, l3});
        System.out.println(res);
    }

}

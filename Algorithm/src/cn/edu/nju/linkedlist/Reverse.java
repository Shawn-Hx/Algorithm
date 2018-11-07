package cn.edu.nju.linkedlist;

import cn.edu.nju.util.ListNode;

/**
 * 翻转单链表
 */
public class Reverse {

    /**
     * 递归实现
     */
    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    /**
     * 循环实现
     */
//    private static ListNode reverse(ListNode head) {
//        if (head == null)
//            return head;
//        ListNode last = null, next = head.next;
//        while (next != null) {
//            head.next = last;
//            last = head;
//            head = next;
//            next = next.next;
//        }
//        head.next = last;
//        return head;
//    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0, 1, 2, 3);
        System.out.println(reverse(head));
    }
}

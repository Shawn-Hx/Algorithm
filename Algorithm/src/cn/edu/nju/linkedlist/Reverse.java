package cn.edu.nju.linkedlist;

import cn.edu.nju.util.Node;

/**
 * 翻转单链表
 */
public class Reverse {

//    /**
//     * 递归实现
//     */
//    private static Node reverse(Node head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        Node reverseHead = reverse(head.next);
//        head.next.next = head;
//        head.next = null;
//        return reverseHead;
//    }

    /**
     * 循环实现
     */
    private static Node reverse(Node head) {
        Node last = head, cur = head.next;
        head.next = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = last;
            last = cur;
            cur = next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(0, 1, 2, 3, 4);
        System.out.println(reverse(head));
    }
}

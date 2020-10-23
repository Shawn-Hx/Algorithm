package cn.edu.nju.linkedlist;

/**
 * LeetCode 206. Reverse Linked list
 */
public class Reverse {

    /**
     * 递归实现
     */
    private static ListNode reverseWithRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reverseHead = reverseWithRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }

    /**
     * 循环实现
     */
    private static ListNode reverseWithoutRecursion(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0, 1, 2, 3);
        System.out.println(reverseWithRecursion(head));
    }
}

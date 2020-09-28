package cn.edu.nju.linkedlist;

/**
 * LeetCode 142. Linked List Cycle II
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if (fast == null || fast.next == null)
            return null;
        ListNode ptr = head;
        while (slow != ptr) {
            slow = slow.next;
            ptr = ptr.next;
        }
        return ptr;
    }

}

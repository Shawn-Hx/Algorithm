package cn.edu.nju.util;

/**
 * 单链表节点
 */
public class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }

    public Node(int... values) {
        this(values[0]);
        Node p = this;
        for (int i = 1; i < values.length; i++) {
            p.next = new Node(values[i]);
            p = p.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        Node p = next;
        while (p != null) {
            sb.append(" -> ");
            sb.append(p.val);
            p = p.next;
        }
        return sb.toString();
    }
}

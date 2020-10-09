package cn.edu.nju.advance;

/**
 * 树状数组 (Binary Indexed Tree / Fenwick Tree)
 * 单点更新，前缀和 O(lgn)
 */
public class BinaryIndexedTree {
    private final int[] tree;
    private final int n;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    private int lowbit(int x) {
        return x & -x;
    }

    // i: [1, n]
    public void update(int i, int delta) {
        while (i <= n) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    // i: [1, n]
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }
}

package cn.edu.nju.unionfind;

public class UF {

    private int count;
    private final int[] fa;
    private final int[] size;

    public UF(int n) {
        this.count = n;
        this.fa = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            fa[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        return x == fa[x] ? x : (fa[x] = find(fa[x]));
    }

    public void union(int x, int y) {
        int xx = find(x);
        int yy = find(y);
        if (xx == yy)
            return;
        // union by rank
        if (size[xx] > size[yy]) {
            fa[yy] = xx;
            size[xx] += size[yy];
        } else {
            fa[xx] = yy;
            size[yy] += size[xx];
        }
        count--;
    }

    public int count() {
        return count;
    }

}

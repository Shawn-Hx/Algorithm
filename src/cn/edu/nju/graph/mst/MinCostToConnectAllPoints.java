package cn.edu.nju.graph.mst;

import java.util.Arrays;

/**
 * LeetCode 1584. Min Cost to Connect All Points
 */
public class MinCostToConnectAllPoints {

    private int manhattanDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visit = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int cur = 0, min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visit[j] && dist[j] < min) {
                    min = dist[j];
                    cur = j;
                }
            }
            visit[cur] = true;
            res += dist[cur];
            for (int j = 0; j < n; j++)
                if (!visit[j])
                    dist[j] = Math.min(dist[j], manhattanDist(points[cur], points[j]));
        }
        return res;
    }

}

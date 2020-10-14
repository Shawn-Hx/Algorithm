package cn.edu.nju.graph.topologicalsort;

import java.util.*;

/**
 * LeetCode 210. Course Schedule II
 */
public class CourseSchedule {

    /** number of vertices */
    private int n;
    /** adjacency list */
    private List<List<Integer>> edges;
    /** in degrees of each vertex */
    private int[] degree;

    // Method 1: DFS

    private boolean dfs(int u, int[] visit, Stack<Integer> stack) {
        visit[u] = 1;
        for (Integer v: edges.get(u)) {
            if (visit[v] == 1)
                return false;
            if (visit[v] == 2)
                continue;
            visit[v] = 1;
            if (!dfs(v, visit, stack))
                return false;
        }
        visit[u] = 2;
        stack.push(u);
        return true;
    }

    private List<Integer> topologicalSortByDFS() {
        Stack<Integer> stack = new Stack<>();
        int[] visit = new int[n];
        for (int i = 0; i < n; i++)
            if (visit[i] == 0 && !dfs(i, visit, stack))
                return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        while (!stack.empty())
            res.add(stack.pop());
        return res;
    }

    // Method 2: Kahn

    private List<Integer> topologicalSortByKahn() {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (degree[i] == 0)
                q.add(i);
        while (!q.isEmpty()) {
            int u = q.remove();
            order.add(u);
            for (int v : edges.get(u))
                if (--degree[v] == 0)
                    q.add(v);
        }
        return order;
    }


    public List<Integer> findOrder(int numCourses, int[][] prerequisites) {
        this.n = numCourses;
        this.degree = new int[numCourses];
        this.edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        for (int[] edge : prerequisites) {
            degree[edge[0]]++;
            edges.get(edge[1]).add(edge[0]);
        }
        List<Integer> order = topologicalSortByKahn();
//        List<Integer> order = topologicalSortByDFS();
        if (order.size() != numCourses)
            return new ArrayList<>();
        return order;
    }

}

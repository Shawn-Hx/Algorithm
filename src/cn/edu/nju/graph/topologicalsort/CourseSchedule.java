package cn.edu.nju.graph.topologicalsort;

import java.util.*;

/**
 * LeetCode 210. Course Schedule II
 */
public class CourseSchedule {

    // Method 1: DFS

    private boolean dfs(int u, List<List<Integer>> edges, int[] visit, Stack<Integer> stack) {
        visit[u] = 1;
        for (Integer v: edges.get(u)) {
            if (visit[v] == 1)
                return false;
            if (visit[v] == 2)
                continue;
            visit[v] = 1;
            if (!dfs(v, edges, visit, stack))
                return false;
        }
        visit[u] = 2;
        stack.push(u);
        return true;
    }

    private List<Integer> topologicalSortByDFS(int n, List<List<Integer>> edges) {
        Stack<Integer> stack = new Stack<>();
        int[] visit = new int[n];
        for (int i = 0; i < n; i++)
            if (visit[i] == 0 && !dfs(i, edges, visit, stack))
                return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        while (!stack.empty())
            res.add(stack.pop());
        return res;
    }

    // Method 2: Kahn

    private List<Integer> topologicalSortByKahn(int n, int[] deg, List<List<Integer>> edges) {
        List<Integer> order = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (deg[i] == 0)
                q.add(i);
        while (!q.isEmpty()) {
            int u = q.remove();
            order.add(u);
            for (int v : edges.get(u))
                if (--deg[v] == 0)
                    q.add(v);
        }
        return order;
    }


    public List<Integer> findOrder(int numCourses, int[][] prerequisites) {
        int[] deg = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        for (int[] edge : prerequisites) {
            deg[edge[0]]++;
            edges.get(edge[1]).add(edge[0]);
        }
        List<Integer> order = topologicalSortByKahn(numCourses, deg, edges);
//        List<Integer> order = topologicalSortByDFS(numCourses, edges);
        if (order.size() != numCourses)
            return new ArrayList<>();
        return order;
    }

}

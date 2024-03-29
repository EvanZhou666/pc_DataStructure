package com.pc.LeetCode.题684.冗余连接;

import com.pc.LeetCode.common.Uitls;

import java.util.*;

/**
 * <p>684. 冗余连接</p>
 * <p>树可以看成是一个连通且 无环 的 无向 图。</p>
 *
 * <p>给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。</p>
 *
 * <p>请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。</p>
 *
 * <img src="https://pic.leetcode-cn.com/1626676174-hOEVUL-image.png"/>
 * <p>输入: edges = [[1,2], [1,3], [2,3]]</p>
 * <p>输出: [2,3]</p>
 */
public class Solution {

    /**
     * dfs深度优先遍历
     * 循环遍历条边（x，y），每次循环做这样一个操作：检查x是否能够连通到y，如果能连通说明发生了环，返回当前边。如果不能，则记录下当前的边
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 节点值为key，value是list集合，存储该节点关联的其他节点
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            // 标记该节点是否访问过
            int[] visited = new int[edges.length + 1];
            if (dfs(x, y, visited, graph)) {
                return edges[i];
            }
            if (!graph.containsKey(x)) {
                graph.put(x, new ArrayList<>(8));
            }

            graph.get(x).add(y);

            if (!graph.containsKey(y)) {
                graph.put(y, new ArrayList<>(8));
            }

            graph.get(y).add(x);
        }
        return new int[0];
    }

    private boolean dfs(int x, int y, int[]visited, Map<Integer, List<Integer>> graph) {
        if (x == y) {
            return true;
        }
        if (visited[x] == 1 || graph.get(x) == null) {
            return false;
        }
        visited[x] = 1;
        for (int i = 0; i < graph.get(x).size(); i++) {
            if (dfs(graph.get(x).get(i), y, visited, graph)) {
                return true;
            }
            
        }

        return false;
    }

    /**
     * bfs广度优先遍历
     * @param edges
     * @return
     */
    public int[] findRedundantConnection2(int[][] edges) {
        // 节点值为key，value是list集合，存储该节点关联的其他节点
        // 含义：存储的边关系
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = edges.length;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int[] visited = new int[n + 1];
            int x = edges[i][0];
            int y = edges[i][1];

            // 1.检查是否存在一条边路径，可以由x达到 y
            // 1.1 如果x是首次出现，那么肯定不存在一条路径可以从x达到y
            if (graph.get(x) == null) {
                List<Integer> target = new ArrayList<>(4);
                target.add(y);
                graph.put(x, target);
            } else {
                if (visited[x] == 0) {
                    queue.offer(x);
                }
                while (!queue.isEmpty()) {
                    Integer poll = queue.poll();
                    // 标记poll已经访问过，防止重复遍历，陷入死循环
                    visited[poll] = 1;
                    // poll 指向的其他节点
                    List<Integer> pollTarget = graph.get(poll);
                    for (int j = 0; j < pollTarget.size(); j++) {
                        // 已经访问过。跳过
                        if (visited[pollTarget.get(j)] == 1) {
                            continue;
                        }
                        if (pollTarget.get(j) == y) {
                            return edges[i];
                        } else {
                            queue.add(pollTarget.get(j));
                        }
                    }
                }
                graph.get(x).add(y);

            }

            if (graph.get(y) == null) {
                List<Integer> target = new ArrayList<>(4);
                target.add(x);
                graph.put(y, target);
            } else {
                graph.get(y).add(x);
            }

        }
        return new int[0];

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] ans = solution.findRedundantConnection(new int[][]{ {1,2}, {1,3}, {2,3}});
//        System.out.println(Arrays.toString(ans));
//        int[] ans2 = solution.findRedundantConnection2(new int[][]{ {1,2}, {1,3}, {2,3}});
//        System.out.println(Arrays.toString(ans2));

//        int[] ans = solution.findRedundantConnection(new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}});
//        int[] ans2 = solution.findRedundantConnection2(new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}});
//        System.out.println(Arrays.toString(ans));
//        System.out.println(Arrays.toString(ans2));

        int[] ans = solution.findRedundantConnection(Uitls.convertToInts("[[1,4],[3,4],[1,3],[1,2],[4,5]]"));
        int[] ans2 = solution.findRedundantConnection2(Uitls.convertToInts("[[1,4],[3,4],[1,3],[1,2],[4,5]]"));
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(ans2));

//        int[] ans = solution.findRedundantConnection(Uitls.convertToInts("[[1,3],[3,4],[1,5],[3,5],[2,3]]"));
//        int[] ans2 = solution.findRedundantConnection2(Uitls.convertToInts("[[1,3],[3,4],[1,5],[3,5],[2,3]]"));
//        System.out.println(Arrays.toString(ans));
//        System.out.println(Arrays.toString(ans2));
    }
}

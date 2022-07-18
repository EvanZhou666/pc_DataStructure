package com.pc.LeetCode.题452;

import com.pc.LeetCode.common.Assert;

import java.util.*;

public class Solution {

    /**
     * 解法总结：
     * 1. 先排序，按气球右边界大小，从小到大排序。
     * 2. 遍历数组，如果当前气球和上一个气球发生重叠，那么在右边界最小的那个气球的右边界射出箭头，引爆气球。例如 当前气球记作current，坐标为current[0],current[1]
     *    上一个气球记作pre，坐标=（pre[0],pre[1]），如果current和pre发生重叠，则引爆气球点= Math.min(pre[1]，current[1])。这里体现的"贪心"的思想，总是尽可能晚的
     *    射出箭头，这样有可能会引爆更多的气球。
     *    如果当前气球和上一个气球没有发生重叠，那么上一个气球pre最晚在pre[1]之前引爆，因为在pre[1]之后的位置射出箭头都无法引爆上一个气球pre了。
     * 3. 代码有坑的位置：
     *    a. 很容易遗漏处理，当前气球和上一个气球没有发生重叠的场景，遗漏引爆上一个气球的。
     *    b. 因为始终是当前气球去和上一个气球发生比较，容易漏掉循环遍历后，没有检查最后一个气球是否有被引爆的。
     *    c. 在循环当初，如果当前气球和上一个气球发生重叠，那么在 Math.min(pre[1]，current[1])的位置引爆气球的时候，当前气球和上一个气球都会被引爆，因此pre定义为
     *       上一个未被引爆的气球，就会导致此时pre不存在了，变为null；并且current之后的气球也有可能因为此次射出的箭被引爆，因此每次循环需要检查当前气球是不是"已经能够被引爆"。
     *       因此，如果当前气球不是"已经能够被引爆"，并且pre等于null，则要重新为pre置值，并且跳过开始下一次遍历。
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {

        if (points.length == 1) {
            return 1;
        }

        // 先排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int campare =  Long.compare(o1[1], o2[1]);
                if (campare == 0) {
                    return Long.compare(o1[0], o2[0]);
                } else {
                    return campare;
                }
            }
        });

         System.out.println("\n----sorted points is------");
         for (int[] point : points) {
         System.out.print(Arrays.toString(point));
         }

         System.out.println("\n---------引爆点is-----");

         // 上一个没有被引爆的气球的坐标
        Integer[] pre = pre(points[0], false);
        List<Long> history = new LinkedList<>();
        boolean preNotBoom = true;
        // 上一个爆炸点💥 初始化为0
        long preBoom = 0;

        for (int i = 1; i < points.length; i++) {

            // 当前气球已经被射爆了
            if (preBoom <= points[i][1] && preBoom >=  points[i][0]) {
                System.out.println(points[i][0] + "<= preBoom="+preBoom+ "<=" + points[i][1]);
                continue;
            }

            if (pre == null) {
                pre = pre(points[i], false);
                preNotBoom = true;
                continue;
            }

            // 如果上一个气球还没有引爆
            if (preNotBoom) {
                if (points[i][0] <= pre[1]) { // 如果当前气球和上一个气球发生重叠，始终移除在右边界较小的位置，记作preBoom处引爆，此时当前气球和上一个气球都会爆炸掉。
                    preBoom = Math.min(pre[1], points[i][1]);
                    System.out.println("preBoom="+ preBoom);
                    pre = pre(points[i], true); // 上一个气球已经被引爆了，所以pre = null
                    preNotBoom = false;
                    history.add(preBoom);
                    continue;
                } else { // 如果当前气球和上一个气球不重叠，则此时必须引爆上一个气球了
                    preBoom = pre[1];
                    history.add(preBoom);
                    pre = pre(points[i], false);
                }
            } else {
                preNotBoom = true;
                pre = pre(points[i], false);
            }

        }

        // 检查最后一个气球有没有被引爆的？没有则引爆
        Long lastBoom = history.get(history.size()-1);
        if (lastBoom < points[points.length -1][0]) {
            history.add((long) points[points.length -1][0]);
        }

        // 兜底 检查pre上一个气球有没有被引爆的, 并且pre不是最后一个气球
        if (pre != null &&  points[points.length -1][0] != pre[0] && points[points.length -1][1] != pre[1]) {
            if (lastBoom < pre[0]) {
                history.add((long) pre[0]);
            }
        }

         System.out.println(history);
        return history.size();

    }

    /**
     * 构造气球坐标，转为Integer[]，主要是为了判断是否存在"上一个还未被引爆的气球"
     * @param point
     * @param isNull
     * @return
     */
    private Integer[] pre(int[] point, boolean isNull) {

        if (isNull) {
            return null;
        }

        Integer[] pre = new Integer[2];
        pre[0] = point[0];
        pre[1] = point[1];
        return pre;
    }

//    /**
//     * 官方解法
//     * @param points
//     * @return
//     */
//    public int findMinArrowShots2(int[][] points) {
//
//        if (points.length == 0) {
//            return 0;
//        }
//
//        Arrays.sort(points, new Comparator<int[]>() {
//            public int compare(int[] point1, int[] point2) {
//                if (point1[1] > point2[1]) {
//                    return 1;
//                } else if (point1[1] < point2[1]) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        });
//
//        System.out.println("----sorted points is------");
//        for (int[] point : points) {
//            System.out.print(Arrays.toString(point));
//        }
//
//        int pos = points[0][1];
//        System.out.println("pos="+pos);
//        int ans = 1;
//        for (int[] balloon: points) {
//            if (balloon[0] > pos) {
//                pos = balloon[1];
//                System.out.println("pos="+pos);
//                ++ans;
//            }
//        }
//        return ans;
//    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = 0;
        ans = solution.findMinArrowShots(new int[][]{{1,2}});
        Assert.assertEquals(ans, 1);

        ans = solution.findMinArrowShots(new int[][]{{0,9},{1,8},{7,8},{1,6},{9,16},{7,13},{7,10},{6,11},{6,9},{9,13}});
        Assert.assertEquals(ans, 3);

        ans = solution.findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}});
        Assert.assertEquals(ans, 2);

        ans = solution.findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}});
        Assert.assertEquals(ans, 2);

        ans = solution.findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}});
        Assert.assertEquals(ans, 4);

        ans = solution.findMinArrowShots(new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}});
        Assert.assertEquals(ans, 2);

        ans = solution.findMinArrowShots(new int[][]{{9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}});
        Assert.assertEquals(ans, 2);

        ans = solution.findMinArrowShots(new int[][]{{1,9},{7,16},{2,5},{7,12},{9,11},{2,10},{9,16},{3,9},{1,3}});
        Assert.assertEquals(ans, 2);

//       ans = solution.findMinArrowShots2(new int[][]{{77171087,133597895},{45117276,135064454},{80695788,90089372},{91705403,110208054},{52392754,127005153},{53999932,118094992},{11549676,55543044},{43947739,128157751},{55636226,105334812},{69348094,125645633}});
//       Assert.assertEquals(ans, 3);

       ans = solution.findMinArrowShots(new int[][]{{77171087,133597895},{45117276,135064454},{80695788,90089372},{91705403,110208054},{52392754,127005153},{53999932,118094992},{11549676,55543044},{43947739,128157751},{55636226,105334812},{69348094,125645633}});
       Assert.assertEquals(ans, 3);
    }
}

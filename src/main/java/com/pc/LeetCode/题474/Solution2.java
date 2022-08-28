package com.pc.LeetCode.题474;

import com.pc.LeetCode.common.Assert;

import java.util.Arrays;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 */
public class Solution2 {

    /**
     * 多维度的0-1背包问题
     * 定义f(n,C,T) 是考虑在n个物品进行选取使得容量<=C、体积<=T的情况下，物品的最大价值。则：
     *  f(n,C,T) = max[ f(n-1,C,T)、 f(n-1,C - weight[n], T - tiji[n]) + 1 ]
     *
     */
    public static int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m+1][n+1];
            int index = 0;
            for(String s: strs){
                char[] sb = s.toCharArray();
                int zeroNum = 0;
                int oneNum = 0;
                for(char c : sb){
                    if(c=='0'){
                        zeroNum++;
                    }
                    else if(c=='1'){
                        oneNum++;
                    }
                }

                // 倒叙是为了重复利用dp数组空间。很难读懂。不建议，但是这样做确实会缩短运行时间和空间。
                for(int i = m; i >= zeroNum; i--){
                    for(int j = n; j >= oneNum; j--){
                        dp[i][j] = Math.max(dp[i][j], dp[i-zeroNum][j-oneNum] + 1);
                    }
                }

                System.out.println("第" + index + "个物品");
                System.out.println(Arrays.toString(strs));
                for (int i = 0; i <=m ; i++) {
                    System.out.println(Arrays.toString(dp[i]));
                }
                index ++;
            }
            return dp[m][n];
    }

    public static void main(String[] args) {
        int ans;
        ans = findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3);
        Assert.assertEquals(ans, 4);

//        ans = findMaxForm(new String[]{"10", "0", "1"}, 1, 1);
//        Assert.assertEquals(ans, 2);
//
//        ans = findMaxForm(new String[]{"10"}, 1, 1);
//        Assert.assertEquals(ans, 1);
//
//        ans = findMaxForm(new String[]{"10","0001"}, 4, 2);
//        Assert.assertEquals(ans, 2);
//
//        ans = findMaxForm(new String[]{"10","0001","111001","1","0"}, 3, 4);
//        Assert.assertEquals(ans, 3);
    }

}

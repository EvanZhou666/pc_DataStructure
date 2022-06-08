package com.pc.LeetCode.题455;//假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
//
// 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[
//i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
//
//
// 示例 1:
//
//
//输入: g = [1,2,3], s = [1,1]
//输出: 1
//解释:
//你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
//虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
//所以你应该输出1。
//
//
// 示例 2:
//
//
//输入: g = [1,2], s = [1,2,3]
//输出: 2
//解释:
//你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
//你拥有的饼干数量和尺寸都足以让所有孩子满足。
//所以你应该输出2.
//
//
//
//
// 提示：
//
//
// 1 <= g.length <= 3 * 10⁴
// 0 <= s.length <= 3 * 10⁴
// 1 <= g[i], s[j] <= 2³¹ - 1
//
// Related Topics 贪心 数组 排序 👍 520 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // 从小到大排序孩子们的胃口
        Arrays.sort(g);
        System.out.println(Arrays.toString(g));
        // 从小到大排序饼干的大小
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));

        int cookie = 0;
        int meetChild = 0;

        // 先满足胃口较小的孩子
        for (int i = 0; i < g.length; i++) {

            while (cookie < s.length) {
                if (s[cookie] >= g[i]) {
                    meetChild ++;
                    cookie ++;
                    break;
                } else {
                    cookie ++;
                }
            }

            // 因为孩子的胃口排好序了，如果轮询到最后一块饼干依然无法满足孩子的胃口，则后面的孩子也不可能被满足
            if (cookie == s.length) {
                return meetChild;
            }

        }
        return meetChild;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //[1,2,5,4]
        //[1,2,3,2,4]
        int count = solution.findContentChildren(new int[]{1,2,5,4},new int[]{1,2,3,2,4});
        System.out.println(count);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

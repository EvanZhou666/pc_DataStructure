package com.pc.LeetCode.题240;

import com.pc.LeetCode.common.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 暴力解法（超时），先固定1个元素，然后再往此元素后面数k个位置，看是否满足nums[i] - nums[j]的绝对值是否小于k
     * @param nums
     * @param k
     * @param t
     * @return
     */
//    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//
//        int length = nums.length;
//        for (int i = 0; i < length; i++) {
////            j < length 防止数组下标越界
//            for (int j = i + 1; j <= i + k && j < length; j++) {
//                if (Math.abs((long)nums[i] - (long) nums[j]) <= t) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

//    错误解法，请无视，不能先对数组进行排序，改变数组索引的位置
//    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        // 先排序
//        Arrays.sort(nums);
//        // 初始化窗口的左右指针
//        int l = 0;
//        int r = k;
//        while (r < nums.length -1) {
//            // 窗口内最后一个元素比第一个元素还要大k，需要缩小窗口
//            if (Math.abs((long)nums[l] - (long) nums[r]) > t) {
//                // 不断缩小窗口的右边界
//                while (l < r) {
//                    if (Math.abs((long)nums[l] - (long) nums[r]) <= t) {
//                        return true;
//                    } else {
//                        r --;
//                    }
//                }
//                // 窗口向右滑动一格， 且伸长窗口至k
//                r = l+k;
//                // 边界，右边界不能超过数组长度
//                if (r > nums.length-1) {
//                    r = nums.length-1;
//                }
//                l = l+1;
//            }
//
//        }
//
//
//
//        return false;
//    }

    /**
     * 【官方题解】桶排序的思路。注意只是用了桶排序的思想，不是真正用桶排序去解题。利用了大小相差t的两个数，可以放在同一个桶；
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            // 计算这个数该放在几号桶
            long id = getID(nums[i], w);
            // 题解巧妙的地方在于，每个桶至多只会有一个元素，如果新加入的元素所在的桶里面已经有一个元素，那么新元素和旧元素肯定是相差在t以内的
            if (map.containsKey(id)) {
                return true;
            }
            // 比较新元素和左边的桶里的元素是否相差t，map.containsKey(id - 1)存在证明存在一个左边桶；
            // Math.abs(nums[i] - map.get(id - 1)) < w ，由于桶里面只有一个元素，那么如果新元素和左边桶的元素绝对值相差t以内，则怎么找到了这样的元素，返回true
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            // 同理 比较新元素和右边桶里的元素是否相差t
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }

            // 如果新元素对应的桶里面不存在旧元素，且和相邻的桶里的元素相差不在t以内，则把新元素放到桶里面。
            map.put(id, (long) nums[i]);
            // 维护所有元素的总个数，至多放k个元素，超过k个元素的时候，要移除掉最早加入进来的那个元素、
            if (i >= k) {
                // 移除桶里面最旧的那个元素
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public static long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    public static void main(String[] args) {
        boolean b = containsNearbyAlmostDuplicate(new int[]{10, 5, 3, 100, 6}, 3, 1);
        Assert.assertIsTrue(b);

//        b = containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2);
//        Assert.assertIsTrue(b);

//        b = containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3);
//        Assert.assertIsFalse(b);

//        [-2147483648,2147483647]
//        boolean b2 = containsNearbyAlmostDuplicate(new int[]{-2147483648,2147483647}, 1, 1);
//        Assert.assertIsFalse(b2);
    }

}

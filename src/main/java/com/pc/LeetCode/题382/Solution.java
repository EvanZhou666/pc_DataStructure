package com.pc.LeetCode.题382;

import com.pc.LeetCode.common.ListNode;

import java.util.Random;

public class Solution {

    ListNode head;
    public Solution(ListNode head) {
        assert head != null;
        this.head = head;
    }

    /**
     * 蓄水池随机抽样算法
     * @return
     */
    public int getRandom() {
        int res = 0;
        int bound = 0;
        ListNode cur = head;
        Random random = new Random();
        while (cur != null) {
            int rand = random.nextInt(++bound);
            System.out.println(rand);
            // 等于0的概率是1/i，因此更新结果返回值
            if (rand == 0) {
                res =  cur.val;
            }
            cur = cur.next;
        }
        return res;
    }


}

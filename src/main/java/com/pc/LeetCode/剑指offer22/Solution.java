package com.pc.LeetCode.剑指offer22;

import com.pc.LeetCode.common.ListNode;

public class Solution {

    /**
     * 快慢指针思路
     * 1.初始化快慢两个指针，初始状态下，快指针比慢指针领先k个位置
     * 2.然后快慢指针保持同频，每次都只往后移动1步，当快指针指向null的时候，慢指针停下来的位置就是倒数第K个位置。
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode slow = head, fast = head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}

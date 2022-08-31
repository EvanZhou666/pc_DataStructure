package com.pc.LeetCode.题61;

import com.pc.LeetCode.common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/rotate-list/">61. 旋转链表</a><br/>
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * <p>
 *     <img src="https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg"/><br/>
 * 输入：head = [1,2,3,4,5], k = 2 <br/>
 * 输出：[4,5,1,2,3]
 * </p>
 * <p>
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * </p>
 */
public class Solution {

    /**
     * 双端队列解法
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            deque.offerLast(cur);
            cur = cur.next;
        }

        k = k % deque.size();
        for (int i = 0; i < k; i++) {
            ListNode last = deque.pollLast();
            last.next = deque.peekFirst();
            deque.offerFirst(last);
            deque.peekLast().next = null;
        }

        return deque.peekFirst();
    }
    
}

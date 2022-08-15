package 每日一题;

/**
 * 24. 两两交换链表中的节点
 *<p> 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。</p>
 * <p><img src="https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg"/></p>
 *
 */
public class _24Solution {

        public ListNode swapPairs(ListNode head) {
            return dfs(head);

        }

        public ListNode dfs(ListNode node) {

            if (node == null) {
                return null;
            }

            if (node.next != null) {
                ListNode suffix = dfs(node.next.next);
                ListNode t = node.next;
                node.next = suffix;
                t.next = node;
                return t;
            } else {
                return node;
            }
        }
}

 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

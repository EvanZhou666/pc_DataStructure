package com.pc.线段树;

/**
 * @Description
 * Leetcode question2
 * @Author evanzhou
 * @Date 2019/4/2 16:23
 **/
public class Solution {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;//代表进位的值 只能是0或者是1
        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode dummyHead3 = new ListNode(0);
        dummyHead1.next = l1;
        dummyHead2.next = l2;
        ListNode iter1 = dummyHead1.next;
        ListNode iter2 = dummyHead2.next;
        ListNode head3 = dummyHead3;
        while (iter1!=null && iter2!=null){
            int intValue = (iter1.val + iter2.val+carry) % 10;
//            carry = 0;//每次用过之后清除carry位
            carry = (iter1.val + iter2.val+carry) / 10;
            ListNode newNode = new ListNode(intValue);
            head3.next = newNode;
            head3 = head3.next;
            iter1 = iter1.next;
            iter2 = iter2.next;
        }
        if (iter1==null && iter2==null&&carry!=0){
            head3.next = new ListNode(carry);
        }

        if (iter1!=null && iter2==null){
            if (carry!=0){
                ListNode subList = addTwoNumbers(new ListNode(carry), iter1);
                head3.next = subList;
            } else {
                head3.next = iter1;
            }
        } else if (iter1==null && iter2!=null){
            if (carry!=0){
                ListNode subList = addTwoNumbers(new ListNode(carry), iter2);
                head3.next = subList;
            } else {
                head3.next = iter2;
            }
        } else if (carry!=0){
            head3.next = new ListNode(carry);
        }
        return dummyHead3.next;
    }

    public static void main(String[] args) {
//        2147483647
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(7);
//        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
//        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(2);
//        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
//        listNode5.next = listNode6;
        ListNode listNode = addTwoNumbers(listNode1, listNode4);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

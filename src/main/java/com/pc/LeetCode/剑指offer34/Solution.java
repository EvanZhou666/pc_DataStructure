package com.pc.LeetCode.剑指offer34;

/**
 * <p>输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 * </p>
 * <p><img src="./sample.png"/></p>
 */
public class Solution {

    private static int count = 0;

    // 打印递归的深度
    private static void print(Object obj) {
        for (int i = 0; i < count; i++) {
            System.out.print("-");
        }
        count ++;
        System.out.println(obj.toString());
    }

    /**
     * 因为后序遍历，则数据的最后一个元素就是根节点。
     * 比根节点大的连续节点就是右子树上的点。
     * 比根节点小的节点以左就是左子树上的点。
     * <p><img src="solution1.png"></p>
     * @param postorder
     * @return
     */
    public static boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }

        int len = postorder.length;
//        print("left start:" + 0 + " end:" + (len - 1));
        try {
            dfs(0, len- 1, postorder);
        } catch (IllegalStateException e) {
            return false;
        }
        return true;
    }

    // int[0] 子树的根节点 int[1] 子树中最大的value值
    private static int[] dfs(int start, int end, int[] postorder) {

        if (start == end) {
            count --;
            return new int[]{postorder[end], postorder[end]};
        }

        // ********************************************************
        // |            这一大坨都是在确定左右子树的区间                |
        // ********************************************************
        int rightStart = -1;
        int leftEnd = -1;
        if (end - 1 >= 0 && postorder[end - 1] < postorder[end]) {
            leftEnd = end - 1;
        } else {
            for (int i = end - 1; i >= 0; i--) {
                if (postorder[i] > postorder[end]) {
                    rightStart = i;
                    leftEnd = rightStart -1;
                } else {
                    break;
                }
            }
        }

        count ++;

        // ********************************************************
        // |                    遍历左子树                          |
        // ********************************************************
        int[] left_root_and_max_value = null;
        // left tree
        if (leftEnd - start >= 0 && start >= 0) {
//            print("left start:" + start + " end:" + leftEnd);
            left_root_and_max_value = dfs(start, leftEnd, postorder);
        }

        // ********************************************************
        // |                    遍历右子树                          |
        // ********************************************************
        int[] right_root_and_max_value = null;;
        if (end - 1 - rightStart >= 0 && rightStart >= 0) {
            // right tree
//            print("right start:" + rightStart + " end:" + (end - 1));
            right_root_and_max_value = dfs(rightStart, end - 1, postorder);
        }

        count --;

        // 左右子树都存在
        if (left_root_and_max_value != null && right_root_and_max_value != null) {
            if (left_root_and_max_value[1] > postorder[end]) {
                throw new IllegalStateException("不合法的状态：左子树中的最大值大于根节点");
            }
            if (right_root_and_max_value[1] < postorder[end]) {
                throw new IllegalStateException("不合法的状态：右子树中的最大值小于根节点");
            }
            return new int[]{ postorder[end], Math.max(postorder[end], Math.max(left_root_and_max_value[1], right_root_and_max_value[1]))};
        }

        // 左右子树都不存在
        else if (left_root_and_max_value == null && right_root_and_max_value == null) {
            return new int[]{postorder[end],postorder[end]};
        }

        // 只有右子树存在
        else if (left_root_and_max_value != null) {
            if (left_root_and_max_value[1] > postorder[end]) {
                throw new IllegalStateException("");
            }
            return new int[]{ postorder[end], Math.max(left_root_and_max_value[1], postorder[end])};
        }
        // 只有左子树存在
        else {
            if (right_root_and_max_value[1] < postorder[end]) {
                throw new IllegalStateException("");
            }
            return new int[]{ postorder[end], Math.max(right_root_and_max_value[1], postorder[end])};
        }
    }

    public static void main(String[] args) {
        verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10});
        verifyPostorder(new int[]{3,2,2,3});
//        verifyPostorder(new int[]{1,6,3,2,5});
    }
}

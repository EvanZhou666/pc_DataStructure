package com.pc.LeetCode.剑指offer34;

public class Solution2 {

    public static boolean verifyPostorder(int[] postorder) {
//        if (postorder.length == 0) {
//            return true;
//        }
        int start = 0;
        int end = postorder.length - 1;
        boolean isSearchTree = dfs(start, end, postorder);
        return isSearchTree;
    }

    /**
     * <img src ="img.png">
     * @param start
     * @param end
     * @param postorder
     * @return
     */
    private static boolean dfs(int start, int end, int[] postorder) {
        if (start == end) {
            return true;
        }
        // 左子树的右区间，不包含leftEnd
        int leftEnd = start;

        while (postorder[leftEnd] < postorder[end]) {
            leftEnd ++;
        }

        // 右子树的右区间，不包含rightEnd
        int rightEnd = leftEnd;
        while (postorder[rightEnd] > postorder[end]) {
            rightEnd ++;
        }

        return rightEnd == end && dfs(start, leftEnd -1, postorder) && dfs(leftEnd, rightEnd - 1, postorder);
    }

    public static void main(String[] args) {
        verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10});
        verifyPostorder(new int[]{3,2,2,3});
        verifyPostorder(new int[]{1,6,3,2,5});
    }
}

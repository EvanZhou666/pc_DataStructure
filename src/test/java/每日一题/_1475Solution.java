package 每日一题;

import java.util.Deque;
import java.util.LinkedList;

public class _1475Solution {

    /**
     * 单调栈的简单应用
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        int[] ans = new int[prices.length];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            }

            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                Integer pop = stack.pop();
                ans[pop] = prices[pop] - prices[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ans[pop] = prices[pop];
        }

        return ans;
    }
    
}

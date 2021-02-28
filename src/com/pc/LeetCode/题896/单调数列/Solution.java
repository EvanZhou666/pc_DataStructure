package com.pc.LeetCode.题896.单调数列;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@author Evan
 *@since 2021/2/28 17:48
 */
public class Solution {


	boolean isAsc;
	boolean isDesc;
	boolean isEqual = true;
	public boolean isMonotonic(int[] A) {
		if (A.length <= 1) {
			return true;
		}

		for (int i=0;i<A.length-1;i++) {
			if (A[i] <A[i+1]) {
				isAsc = true;
				isEqual = false;
			} else if (A[i] >A[i+1]) {
				isDesc = true;
				isEqual = false;
			}

			if (isAsc && isDesc) {
				return false;
			}
		}

		return isAsc || isDesc ||isEqual;
	}

}

package com.vivek.array.pattern.maxmin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Find the max sum of contiguous sub-array.
 * For ex: for array [-2, -3, 4, -1, -2, 1, 5, -3], the max sum sub array is [4, -1, -2, 1, 5] with sum = 7.
 * 
 * The problem is solved by dynamic programming
 * initialize: max_so_far = 0
 * max_ending_here = 0
 * Loop for each element of the array
 * (a) max_ending_here = max_ending_here + a[i]
 * (b) if(max_ending_here < 0) max_ending_here = 0
 * (c) if(max_so_far < max_ending_here) max_so_far = max_ending_here
 * return max_so_far
 * 
 * Explanation: Simple idea of the Kadane's algorithm is to look for all positive contiguous segments of the array (max_ending_here is used for this). And keep track of maximum sum contiguous segment among all positive segments (max_so_far is used for this). Each time we get a positive sum compare it with max_so_far and update max_so_far if it is greater than max_so_far
 * Lets take the example:
 * {-2, -3, 4, -1, -2, 1, 5, -3}
 * 
 * max_so_far = max_ending_here = 0
 * 
 * for i=0, a[0] = -2 max_ending_here = max_ending_here + (-2) Set max_ending_here = 0 because max_ending_here < 0
 * for i=1, a[1] = -3 max_ending_here = max_ending_here + (-3) Set max_ending_here = 0 because max_ending_here < 0
 * for i=2, a[2] = 4 max_ending_here = max_ending_here + (4) max_ending_here = 4 max_so_far is updated to 4 because max_ending_here greater than max_so_far which was 0 till now
 * for i=3, a[3] = -1 max_ending_here = max_ending_here + (-1) max_ending_here = 3
 * for i=4, a[4] = -2 max_ending_here = max_ending_here + (-2) max_ending_here = 1
 * for i=5, a[5] = 1 max_ending_here = max_ending_here + (1) max_ending_here = 2
 * for i=6, a[6] = 5 max_ending_here = max_ending_here + (5) max_ending_here = 7 max_so_far is updated to 7 because max_ending_here is greater than max_so_far
 * for i=7, a[7] = -3 max_ending_here = max_ending_here + (-3) max_ending_here = 4
 *
 */

public class MaxSumSubarrayKadanesAlgo {
	
	public static void main(String... args) {
		int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println(maxSumSubArray(nums));
	}
	
	private static int maxSumSubArray(int[] arr) {
		
		int maxEndingHere = 0;
		int maxSoFar = Integer.MIN_VALUE;

		for (int num : arr) {
			if (maxEndingHere < 0) {
				maxEndingHere = 0;
			}

			maxEndingHere += num;

			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		
		return maxSoFar;
	}

}

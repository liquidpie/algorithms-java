package com.vivek.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Longest Increasing Subsequence Size (N log N)
 *
 * Given an array arr[] of size N, the task is to find the length of the Longest Increasing Subsequence (LIS)
 * i.e., the longest possible subsequence in which the elements of the subsequence are sorted in increasing order, in O(N log N).
 *
 * Examples:
 *     Input: arr[] = {3, 10, 2, 1, 20}
 *     Output: 3
 *     Explanation: The longest increasing subsequence is 3, 10, 20
 *
 *     Input: arr[] = {3, 2}
 *     Output:1
 *     Explanation: The longest increasing subsequences are {3} and {2}
 *
 *     Input: arr[] = {50, 3, 10, 7, 40, 80}
 *     Output: 4
 *     Explanation: The longest increasing subsequence is {3, 7, 40, 80}
 *
 * Approach:
 *
 * This solution uses the Patience Sorting algorithm (check princeton link)
 *
 * 1. Create a piles list where each index is the pile itself
 * 2. Instead of storing the complete pile data, we store only the last element of the pile
 * 3. If the current number while iterating is greater than the last pile's value, a new pile is created
 * 4. Else, we do binary search to find the pile to store the current value
 * 5. The total number of piles will represent the increasing subsequence length
 *
 * Nums:
 * [10,9,2,5,3,7,101,18]
 *
 * Final piles:
 *
 * piles[0]  piles[1]  piles[2]  piles[3]
 * 10        5         7         101
 * 9         3                   18
 * 2
 *
 * Piles updating procedure:
 * piles[0] = 10
 * piles[0] = 9
 * piles[0] = 2
 * piles[1] = 5
 * piles[1] = 3
 * piles[2] = 7
 * piles[3] = 101
 * piles[3] = 18
 *
 * A longest increasing subsequence:
 * 2,3,7,18
 *
 * Length of LIS:
 * 4, which is the number of piles.
 *
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/solutions/74824/java-python-binary-search-o-nlogn-time-with-explanation/
 *
 * https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
 *
 * Time Complexity: O(n*log(n)) where n is the size of the input vector nums.
 * Auxiliary Space: O(n)
 *
 * Reference:
 *
 * https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 */
public class LongestIncreasingSubsequenceBinarySearchConstruction {

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(constructLIS(nums));
    }

    static List<Integer> constructLIS(int[] nums) {
        List<Node> piles = new ArrayList<>(nums.length);
        piles.add(new Node(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            Node node = new Node(nums[i]);
            if (node.compareTo(piles.get(piles.size() - 1)) > 0) {
                node.prev = piles.get(piles.size() - 1);
                piles.add(node); // create new pile
            } else {
                // find the pile to add this number
                int pile = Collections.binarySearch(piles, node);
                if (pile < 0) pile = ~pile; // changes -1 to 0, since negative numbers are represented as 2's complement
                if (pile > 0) {
                    node.prev = piles.get(pile - 1);
                }
                piles.set(pile, node);
            }
        }
        return extractLIS(piles);
    }

    private static List<Integer> extractLIS(List<Node> piles) {
        List<Integer> result = new ArrayList<>(piles.size());
        for (Node curr = piles.isEmpty() ? null : piles.get(piles.size() - 1); curr != null; curr = curr.prev) {
            result.add(curr.val);
        }
        Collections.reverse(result);
        return result;
    }

    static class Node implements Comparable<Node> {
        int val;
        Node prev;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.val, that.val);
        }
    }
}

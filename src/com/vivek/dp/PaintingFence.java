package com.vivek.dp;

/**
 * Painting Fence
 *
 * Given a fence with n posts and k colors, find out the number of ways of painting the fence such that at most 2 adjacent
 * posts have the same color. Since the answer can be large return it modulo 10^9 + 7.
 *
 * Examples:
 *
 * Input : n = 2 k = 4
 * Output : 16
 * Explanation: We have 4 colors and 2 posts.
 * Ways when both posts have same color : 4
 * Ways when both posts have diff color :4(choices for 1st post) * 3(choices for 2nd post) = 12
 *
 * Input : n = 3 k = 2
 * Output : 6
 *
 * The following image depicts the 6 possible ways of painting 3 posts with 2 colors:
 *
 *
 *
 * Consider the following image in which c, c’ and c” are the respective colors of posts i, i-1, and i -2.
 *
 *
 *
 * According to the constraint of the problem, c = c’ = c” is not possible simultaneously, so either c’ != c or c” != c or both. There are k – 1 possibility for c’ != c and k – 1 for c” != c.
 *
 *  diff = no of ways when color of last
 *         two posts is different
 *  same = no of ways when color of last
 *         two posts is same
 *  total ways = diff + same
 *
 * for n = 1
 *     diff = k, same = 0
 *     total = k
 *
 * for n = 2
 *     diff = k * (k-1) //k choices for
 *            first post, k-1 for next
 *     same = k //k choices for common
 *            color of two posts
 *     total = k +  k * (k-1)
 *
 * for n = 3
 *     diff = (previous total ways) * (k - 1)
 *            (k + k * (k - 1) * (k - 1)
 *     same = previous diff ways
 *            k * (k-1)
 *
 *
 * Hence we deduce that,
 * total[i] = same[i] + diff[i]
 * same[i]  = diff[i-1]
 * diff[i]  = total[i-1] * (k-1)
 *
 * Time Complexity: O(N)
 * Auxiliary Space: O(N)
 *
 * https://www.geeksforgeeks.org/painting-fence-algorithm/
 */
public class PaintingFence {

    public static void main(String[] args) {
        int n = 3, k = 2;
        System.out.println(countWays(n, k));
    }

    static int countWays(int n, int k) {
        int[] dp = new int[n + 1];

        // There are k ways to color first post
        dp[1] = k;
        int mod = 100_000_007;

        // There are 0 ways for single post to violate (same color_ and k ways to not violate (different color)
        int same = 0, diff = k;
        // Fill for 2 posts onwards
        for (int i = 2; i <= n; i++) {
            // Current same is same as previous diff
            same = diff;
            // We always have k-1 choices for next post
            diff = (dp[i - 1] * (k - 1)) % mod;
            // Total choices till i.
            dp[i] = (same + diff) % mod;
        }

        return dp[n];
    }

}

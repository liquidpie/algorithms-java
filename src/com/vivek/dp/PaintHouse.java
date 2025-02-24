package com.vivek.dp;

/**
 * 256. Paint House
 *
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Return the minimum cost to paint all houses.
 *
 *
 * Example 1:
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 *
 * Example 2:
 * Input: costs = [[7,6,2]]
 * Output: 2
 *
 * Solution: One of the best explanation on DP problem
 * https://leetcode.com/problems/paint-house/editorial/
 *
 * Approach: DP
 *
 * First thing to realize is that we don't need to do anything to the last row. Like in the tree,
 * these costs are the total costs because there are no further houses after them.
 * Now, what about the second-to-last row? Well, we know that if we painted that house red,
 * that it'd cost itself and the cheapest out of blue and green from the next row, which is 8.
 * So the total cost there would be 14, and we can put that into the cell.
 *
 * Just like we did with the tree, we can work our way up through the grid, repeatedly applying the same algorithm to
 * determine the total value for each cell. Once we have updated all the cells, we then simply need to take the minimum
 * value from the first row and return it. Here is an animation showing the process.
 *
 * Algorithm -
 *
 * The algorithm is straightforward. We iterate backwards over all the rows in the grid (starting from the second-to-last)
 * and calculate a total cost for each cell in the way shown in the animation.
 * You could also avoid the hardcoding of the colors and instead iterate over the colors.
 * This approach will be covered in the solution article for the follow up question where there are m colors instead of just 3.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Reference:
 * https://leetcode.com/problems/paint-house/description/
 */
public class PaintHouse {

    public static void main(String[] args) {
        int[][] costs = {{17,2,17}, {16,16,5}, {14,3,19}};
        System.out.println(minCost(costs)); // Output: 10
    }

    static int minCost(int[][] costs) {
        if (costs.length == 0)
            return 0;

        for (int i = costs.length - 2; i >= 0; i--) {
            // when the current house is chosen as RED
            costs[i][0] += Math.min(costs[i + 1][1], costs[i + 1][2]);
            // when the current house is chosen as BLUE
            costs[i][1] += Math.min(costs[i + 1][0], costs[i + 1][2]);
            // when the current house is chosen as GREEN
            costs[i][2] += Math.min(costs[i + 1][0], costs[i + 1][1]);
        }

        return Math.min(costs[0][2], Math.min(costs[0][0], costs[0][1]));
    }

}

package com.vivek.dp;

/**
 * 265. Paint House II
 *
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Return the minimum cost to paint all houses.
 *
 * Example 1:
 *
 * Input: costs = [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation:
 * Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 *
 * Example 2:
 *
 * Input: costs = [[1,3],[2,4]]
 * Output: 5
 *
 * Solution:
 * https://leetcode.com/problems/paint-house-ii/editorial/
 *
 * The problem we're trying to solve is equivalent to the following:pick exactly one number from each row such that
 * the sum of those numbers is minimized. Because 2 adjacent houses cannot be the same color,adjacent rows must be picked
 * from different columns. This is a straightforward variant of one of those "classic" minimum-path-in-a-grid dynamic programming problems.
 *
 * The way that we solve it is to iterate over the cells and determine what the cheapest way of getting to that cell is. We'll work from top to bottom.
 *
 * To begin with, we say the first row (house 0) is already completed. We don't need to make any changes to it.
 *
 * Then, for each cell in the second row, we work out the cheapest way of getting to it from the first row is.
 * For example, to get to[1][red]we have to go through any of the non-red cells from the row above. We want to go through the minimum.
 *
 * We show our decision by updating[1][red]to7 + 6 = 13.
 *
 * We can repeat this for the rest of the second row, and then work down each of the remaining rows.
 *
 * Here's an animation of the algorithm being carried out.
 *
 * When we're finished, the final answer is theminimum value in the last row.
 *
 * Time Complexity: O(n * k^2)
 * Space Complexity: O(1)
 *
 * Approach 4: Dynamic programming with Optimized Time
 * ---------------------------------------------------
 * Despite Paint House II being listed as a hard question, and the problem statement listingO(n⋅k)time as a "follow up",
 * you'd possibly be expected to come up with this solution at top companies as it's still a fairly basic dynamic programming algorithm.
 * You should, therefore, ensure you're comfortable with this approach and could identify and apply similar observations
 * in other dynamic programming problems. At the very least, it'll make you look awesome!
 *
 * So far, all of our approaches have had aO(n⋅k^2) time complexity.
 * This is because calculating the new value for each of theO(n⋅k)cells required looking at each of the k cells in the row immediately below.
 *
 * However, we don't need to look at the entire previous row for every cell. Let's look again at the large example from above.
 * When we're calculating the values for the second row, we're adding the minimum from the first row onto them.
 * The only cell we can't do this for is the one that was directly below the minimum, as this would break the adjacency rule.
 * For this one, it makes sense to add the second minimum.
 *
 * Time Complexity: O(n * k)
 * Space Complexity: O(1)
 *
 * Reference:
 * https://leetcode.com/problems/paint-house-ii/description/
 */
public class PaintHouseII {

    public static void main(String[] args) {
        int[][] costs = { {1,5,3}, {2,9,4} };
        System.out.println(minCostII(costs)); // output: 5
    }

    static int minCostII(int[][] costs) {

        if (costs.length == 0)
            return 0;

        int n = costs.length;
        int k = costs[0].length;

        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j)
                        min = Math.min(min, costs[i + 1][l]);
                }
                costs[i][j] += min;
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minCost = Math.min(minCost, costs[0][i]);
        }
        return minCost;
    }

    static int minCostIIOptimized(int[][] costs) {
        if (costs.length == 0) return 0;
        int k = costs[0].length;
        int n = costs.length;

        for (int house = 1; house < n; house++) {

            // Find the minimum and second minimum color in the PREVIOUS row.
            int minColor = -1; int secondMinColor = -1;
            for (int color = 0; color < k; color++) {
                int cost = costs[house - 1][color];
                if (minColor == -1 || cost < costs[house - 1][minColor]) {
                    secondMinColor = minColor;
                    minColor = color;
                } else if (secondMinColor == -1 || cost < costs[house - 1][secondMinColor]) {
                    secondMinColor = color;
                }
            }

            // And now calculate the new costs for the current row.
            for (int color = 0; color < k; color++) {
                if (color == minColor) {
                    costs[house][color] += costs[house - 1][secondMinColor];
                } else {
                    costs[house][color] += costs[house - 1][minColor];
                }
            }
        }

        // Find the minimum in the last row.
        int min = Integer.MAX_VALUE;
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }

}

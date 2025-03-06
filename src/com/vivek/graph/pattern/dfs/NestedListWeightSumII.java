package com.vivek.graph.pattern.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 364. Nested List Weight Sum II
 *
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose
 * elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1]
 * has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.
 *
 * The weight of an integer is maxDepth - (the depth of the integer) + 1.
 *
 * Return the sum of each integer in nestedList multiplied by its weight.
 *
 * Example 1:
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
 * 1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
 *
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
 * 1*3 + 4*2 + 6*1 = 17
 *
 * Solution: https://leetcode.com/problems/nested-list-weight-sum-ii/editorial/
 *
 * Approach 1: Double Pass Depth-First Search (DFS)
 *
 * To calculate the weight (maxDepth - depth + 1) of any integer, we must first find the maximum depth of the given nested list.
 * So to find the maximum depth, we can iterate over the elements in the given nested list, and the maximum depth of
 * the list will be the maximum depth of any element inside the list. If the list only contains integers, then its depth is 1.
 * However, if the list contains other nested lists, then its depth is 1 plus the maximum depth of these nested lists.
 * Thus, we can recursively call our findMaxDepth function on any nested list to find the maximum depth.
 *
 * The recursive function findMaxDepth traverses over the NestedInteger and recursively explores each nested list.
 * The depth of the current nested list will be one (for the current level) plus the maximum depth among all of the
 * nested lists that it contains. If a nested list only contains integers, then return 1.
 *
 * Approach 2: Single Pass DFS
 *
 * In the previous approach, we perform DFS twice. Can we do this in a single traversal?
 * The reason for doing DFS two times is that we need maxdepth to find the integer's weight, hence we have to
 * find the maxdepth in advance to calculate the weight. If we can somehow pull out the maxDepth from weight definition
 * to an independent term, we can solve the problem in a single traversal.
 *
 * $$\sum_{i=1}^{N} a_{i} * weight_{i}$$
 *
 * = $$\sum_{i=1}^{N} a_{i} * (maxDepth - depth_{i} + 1)$$
 *
 * = $$\sum_{i=1}^{N} (a_{i} * maxDepth - a_i * depth_{i}+ a_i)$$
 *
 * = $$\sum_{i=1}^{N} a_{i} * maxDepth$$ - $$\sum_{i=1}^{N} a_i * depth_{i}$$ + $$\sum_{i=1}^{N} a_{i} $$
 *
 * = $$maxDepth * \sum_{i=1}^{N} a_{i} $$ - $$\sum_{i=1}^{N} a_i * depth_{i}$$ + $$1 * \sum_{i=1}^{N} a_{i} $$
 *
 * = $$(maxDepth + 1) * \sum_{i=1}^{N} a_{i} $$ - $$\sum_{i=1}^{N} a_i * depth_{i}$$
 *
 * = $$(maxDepth + 1) * sumOfElements$$ - $$sumOfProducts$$
 *
 * Notice that maxDepth is now outside of the summation. Thus, we do not need to use maxDepth until the last step in our calculation.
 * Therefore we can find the maxDepth at the same time that we perform a depth-first traversal to find the sum of all a_{i} values
 * (sumOfElements) and the sum of all a_{i} * depth_{i} values (sumOfProducts).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Reference: https://leetcode.com/problems/nested-list-weight-sum-ii/description/
 */
public class NestedListWeightSumII {

    public static void main(String[] args) {
        {
            // [[1,1],2,[1,1]]
            List<NestedInteger> input = new ArrayList<>();
            var child1 = new NestedInteger();
            child1.add(new NestedInteger(1));
            child1.add(new NestedInteger(1));
            var child2 = new NestedInteger(2);
            var child3 = new NestedInteger();
            child3.add(new NestedInteger(1));
            child3.add(new NestedInteger(1));
            input.add(child1);
            input.add(child2);
            input.add(child3);

            System.out.println(depthSumInverse(input)); // 8
        }

        {
            // [1,[4,[6]]]
            List<NestedInteger> input = new ArrayList<>();
            var child1 = new NestedInteger(1);
            var child2 = new NestedInteger();
            child2.add(new NestedInteger(4));
            var child3 = new NestedInteger();
            child3.add(new NestedInteger(6));
            child2.add(child3);
            input.add(child1);
            input.add(child2);

            System.out.println(depthSumInverse(input)); // 17
        }
    }

    static int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth = findMaxDepth(nestedList);
        return dfs(nestedList, maxDepth, 1);
    }

    private static int findMaxDepth(List<NestedInteger> nestedList) {
        int maxDepth = 1;
        for (NestedInteger nestedInteger : nestedList) {
            if (!nestedInteger.isInteger() && !nestedInteger.getList().isEmpty()) {
                maxDepth = Math.max(maxDepth, 1 + findMaxDepth(nestedInteger.getList()));
            }
        }
        return maxDepth;
    }

    private static int dfs(List<NestedInteger> nestedList, int maxDepth, int depth) {
        int sum = 0;
        for (NestedInteger value : nestedList) {
            if (value.isInteger()) {
                sum += value.getInteger() * (maxDepth - depth + 1);
            } else {
                sum += dfs(value.getList(), maxDepth, depth + 1);
            }
        }
        return sum;
    }

    static class Approach2 {
        static class WeightedSumTriplet {
            int maxDepth;
            int sumOfElements;
            int sumOfProducts;

            public WeightedSumTriplet(int maxDepth, int sumOfElements, int sumOfProducts) {
                this.maxDepth = maxDepth;
                this.sumOfElements = sumOfElements;
                this.sumOfProducts = sumOfProducts;
            }
        }

        static int depthSumInverse(List<NestedInteger> nestedList) {
            WeightedSumTriplet weightedSumTriplet = getWeightedSumTriplet(nestedList, 1);

            int maxDepth = weightedSumTriplet.maxDepth;
            int sumOfElements = weightedSumTriplet.sumOfElements;
            int sumOfProducts = weightedSumTriplet.sumOfProducts;

            return (maxDepth + 1) * sumOfElements - sumOfProducts;
        }

        private static WeightedSumTriplet getWeightedSumTriplet(List<NestedInteger> nestedList, int depth) {
            int maxDepth = 0;
            int sumOfElements = 0;
            int sumOfProducts = 0;

            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    maxDepth = Math.max(maxDepth, depth);
                    sumOfElements += nestedInteger.getInteger();
                    sumOfProducts += nestedInteger.getInteger() * depth;
                } else {
                    WeightedSumTriplet result = getWeightedSumTriplet(nestedInteger.getList(), depth + 1);
                    sumOfElements += result.sumOfElements;
                    sumOfProducts += result.sumOfProducts;
                    maxDepth = Math.max(maxDepth, result.maxDepth);
                }
            }
            return new WeightedSumTriplet(maxDepth, sumOfElements, sumOfProducts);
        }

    }

    static class NestedInteger {
        private final List<NestedInteger> list;
        private Integer intValue;

        // Constructor initializes an empty nested list.
        public NestedInteger() {
            this.list = new ArrayList<>();
            this.intValue = null;
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            this.list = null;
            this.intValue = value;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return list == null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // The result is undefined if this NestedInteger holds a nested list
        public Integer getInteger() {
            return intValue;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.intValue = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // The result is undefined if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return list;
        }
    }

}

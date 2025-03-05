package com.vivek.graph.pattern.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 339. Nested List Weight Sum
 *
 * You are given a nested list of integers nestedList. Each element is either an integer or
 * a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of.
 * For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 * Example 1:
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 *
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 *
 * Example 3:
 * Input: nestedList = [0]
 * Output: 0
 *
 * Solution:
 * https://leetcode.com/problems/nested-list-weight-sum/editorial/
 *
 * Time complexity : O(N).
 * Space complexity : O(N).
 *
 * In terms of space, at most O(D) recursive calls are placed on the stack, where D is the maximum level of nesting in the input.
 * For example, D=2 for the input [[1,1],2,[1,1]], and D=3 for the input [1,[4,[6]]].
 *
 * In the worst case, D=N, (e.g. the list [[[[[[]]]]]]) so the worst-case space complexity is O(N).
 *
 * Reference:
 * https://leetcode.com/problems/nested-list-weight-sum/description/
 */
public class NestedListWeightSum {

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

            System.out.println(depthSum(input)); // 10
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

            System.out.println(depthSum(input)); // 27
        }
    }

    static int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private static int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger value : nestedList) {
            if (value.isInteger()) {
                sum += value.getInteger() * depth;
            } else {
                sum += dfs(value.getList(), depth + 1);
            }
        }
        return sum;
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

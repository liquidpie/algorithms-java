package com.vivek.numbers;

/**
 * A string is given like this [1[2,[3,4] ] ] and you need to return the result which is calculated like this 3*1 + 4*1 +2*2 + 1*3 => 14,
 * i.e 1 has multiplication factor(mf) of 3, 2 has mf of 2 and (3 and 4 ) has mf of 1 since they are at the deepest level.
 * Needed to write the optimal code for this.
 *
 * Gave the solution with extra space but recruiter wanted it without any extra space except for recursion stack so did it like that.
 */
public class DepthSum {

    public static void main(String[] args) {
        String expr = "[1[2,[3,4],[5,6]]]";

        int result = depthSum(expr);

        System.out.println(result);
    }

    static int depthSum(String expr) {
        int maxDepth = maxDepth(expr);
        int sum = 0;
        int i = 0;

        int currentDepth = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '[') {
                currentDepth++;
            } else if (expr.charAt(i) == ']') {
                currentDepth--;
            } else if (expr.charAt(i) != ',' && expr.charAt(i) != ' ') {
                sum += Integer.parseInt(String.valueOf(expr.charAt(i))) * (maxDepth - currentDepth + 1);
            }
            i++;
        }
        return sum;
    }

    private static int maxDepth(String expr) {
        int maxDepth = 0;
        int depth = 0;
        for (char ch : expr.toCharArray()) {
            if (ch == '[')
                depth++;
            else if (ch == ']')
                depth--;
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

}
